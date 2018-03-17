package com.expense.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.expense.entity.Expense;
import com.expense.service.ExpenseService;

import javax.ws.rs.core.MediaType;

@Path("")
public class ExpenseController {

	ExpenseService es = new ExpenseService();

	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
		String output = "Jersey say " + msg;
		return Response.status(200).entity(output).build();

	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Expense> getExpenses() {
		return ExpenseService.getExpenses();
	}

	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Expense addExpense(Expense newExpense) {
		return ExpenseService.addExpense(newExpense);
	}
	
	

}
