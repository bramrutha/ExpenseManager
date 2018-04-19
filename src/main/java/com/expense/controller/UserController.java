package com.expense.controller;

import com.expense.entity.User;
import com.expense.exception.DataBaseException;
import com.expense.service.UserService;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
public class UserController {
	
	UserService userService = new UserService();

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser(User newUser) {		
		
		try {
			userService.addUser(newUser);
			return Response.ok().entity(newUser).header("Content-Type", MediaType.APPLICATION_JSON).build();
		} catch (DataBaseException e) {
			System.out.println("Catching SQL Exception");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage())
					.header("Content-Type", MediaType.TEXT_PLAIN).build();
		}

	}

	@GET
	@Path("/list")
	public Response getAll() {
		
		try {
			List<User> userList = new ArrayList<User>();
			userList = userService.getAll();
			return Response.ok().entity(userList).header("Content-Type", MediaType.APPLICATION_JSON).build();
		} catch (DataBaseException e) {
			System.out.println("Catching SQL Exception");
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage())
					.header("Content-Type", MediaType.TEXT_PLAIN).build();
		}

	}
}
