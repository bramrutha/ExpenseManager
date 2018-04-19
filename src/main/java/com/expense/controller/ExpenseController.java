package com.expense.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.expense.exception.DataBaseException;
import com.expense.service.ExpenseService;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("")
public class ExpenseController {
	
	ExpenseService expenseService = new ExpenseService();

	@POST
	@Path("/add/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addExpense(@PathParam("id") int userId, Expense newExpense) {

		try {			
			expenseService.addExpense(userId, newExpense);
			return Response.ok().entity(newExpense).header("Content-Type", MediaType.APPLICATION_JSON).build();

		} catch (DataBaseException e) {
			System.out.println("Catching SQL Exception");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage())
					.header("Content-Type", MediaType.TEXT_PLAIN).build();
		}

	}

	@GET
	@Path("/list/{userId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAll(@PathParam("userId") int userId) {
		
		List<Expense> expenseList = new ArrayList<Expense>();
		
		try {
			
			expenseList = expenseService.getAll(userId);
			return Response.ok().entity(expenseList).header("Content-Type", MediaType.APPLICATION_JSON).build();
			
		} catch (DataBaseException e) {
			System.out.println("Catching SQL Exception");
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage())
					.header("Content-Type", MediaType.TEXT_PLAIN).build();
		}

	}

	@GET
	@Path("/list/range")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getRangeExpense(@QueryParam("id") int userId, @QueryParam("from") String fromDate,
			@QueryParam("to") String toDate) {

		List<Expense> expenseList = new ArrayList<Expense>();
		
		try {
			expenseList = expenseService.getRangeExpense(userId, fromDate, toDate);
			return Response.ok().entity(expenseList).header("Content-Type", MediaType.APPLICATION_JSON).build();
		} catch (DataBaseException e) {
			System.out.println("Catching SQL Exception");
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage())
					.header("Content-Type", MediaType.TEXT_PLAIN).build();
		}

	}

	@GET
	@Path("/list/cat")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDayCatExpense(@QueryParam("id") int userId, @QueryParam("from") String fromDate,
			@QueryParam("to") String toDate) {

		Map<String, Double> categoryMap = new HashMap<String, Double>();
	
		try {
			categoryMap = expenseService.getDayCatExpense(userId, fromDate, toDate);
			return Response.ok().entity(categoryMap).header("Content-Type", MediaType.APPLICATION_JSON).build();
		} catch (DataBaseException e) {
			System.out.println("Catching SQL Exception");
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage())
					.header("Content-Type", MediaType.TEXT_PLAIN).build();
		}

	}

	@PUT
	@Path("/update/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int userId, Expense expense) {
		
		try {
			expenseService.update(userId, expense);
			return Response.ok().entity(expense).header("Content-Type", MediaType.APPLICATION_JSON).build();
		} catch (DataBaseException e) {
			System.out.println("Catching SQL Exception");
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage())
					.header("Content-Type", MediaType.TEXT_PLAIN).build();
		}

	}

	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") int expenseId) {
		
		try {
			expenseService.delete(expenseId);
			return Response.ok().entity("Delete successful").header("Content-Type", MediaType.APPLICATION_JSON).build();
		} catch (DataBaseException e) {
			System.out.println("Catching SQL Exception");
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage())
					.header("Content-Type", MediaType.TEXT_PLAIN).build();
		}

	}
}
