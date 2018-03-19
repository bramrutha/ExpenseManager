package com.expense.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


import com.expense.entity.Expense;
import com.expense.service.ExpenseService;

import javax.ws.rs.core.MediaType;

@Path("")
public class ExpenseController {

	
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Expense addExpense(Expense newExpense) {
		return ExpenseService.addExpense(newExpense);
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Expense> getAll() {
		return ExpenseService.getAll();
	}
	
	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Expense getById(@PathParam("id") long gid) {
		return ExpenseService.getById(gid);
	}
	
	@PUT
	@Path("/update/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Expense update(@PathParam("id") long id, Expense exp) {
		return ExpenseService.update(exp);
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON) 
	public void delete(@PathParam("id") long id) {
		ExpenseService.delete(id);
		
	}
	}
	
	
	
	
	
	
	


