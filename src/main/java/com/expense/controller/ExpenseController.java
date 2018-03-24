package com.expense.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.expense.entity.Expense;
import com.expense.service.ExpenseService;
import javax.ws.rs.core.MediaType;

@Path("")
public class ExpenseController {

	
	@POST
	@Path("/add/{id}") 
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Expense addExpense(@PathParam("id") int userId,Expense newExpense) throws ClassNotFoundException, SQLException 
	{
		return ExpenseService.addExpense(userId,newExpense);
	}

	
	@GET
	@Path("/list/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Expense> getAll(@PathParam("id") int userId) throws ClassNotFoundException, SQLException {
		return ExpenseService.getAll(userId);
	}
	
	@GET
	@Path("/list/range")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Expense> getRangeExpense(@QueryParam("id") int userId,
			@QueryParam("from") String fromDate,@QueryParam("to") String toDate) 
			                                                            throws ClassNotFoundException, SQLException, ParseException {
		return ExpenseService.getRangeExpense(userId,fromDate,toDate);
	}
	
	
	@GET
	@Path("/list/cat")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String,Double> getDayCatExpense(@QueryParam("id") int userId,@QueryParam("from") String fromDate,@QueryParam("to") String toDate) 
			                                                            throws ClassNotFoundException, SQLException, ParseException {
		return ExpenseService.getDayCatExpense(userId,fromDate,toDate);
	}
	
  	
    @PUT
	@Path("/update/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Expense update(@PathParam("id") int userId, Expense expense ) throws ClassNotFoundException{
		return ExpenseService.update(userId,expense);
	}


	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON) 
	public void delete(@PathParam("id") int expenseId) throws ClassNotFoundException {
		ExpenseService.delete(expenseId);
	}
}

	
	
	
	
	
	
	


