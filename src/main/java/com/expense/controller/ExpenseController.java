package com.expense.controller;

import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
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
	public Expense addExpense(@PathParam("id") int userId,Expense newExpense) throws ClassNotFoundException, SQLException 
	{
		return ExpenseService.addExpense(userId,newExpense);
	}
}
	/*
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Expense> getAll(@PathParam("id") long userId) {
		return ExpenseService.getAll(userId);
	}
	
	
    @PUT
	@Path("/update/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Expense update(@PathParam("id") long userId, Expense expense) {
		return ExpenseService.update(userId,expense);
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON) 
	public void delete(@PathParam("id") long id) {
		ExpenseService.delete(id);
		
	}
	}
	
	*/
	
	
	
	
	


