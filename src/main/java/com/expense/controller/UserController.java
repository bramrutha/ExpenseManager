package com.expense.controller;

import com.expense.entity.User;
import com.expense.service.UserService;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("user")
public class UserController {

	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User addUser(User newUser) throws ClassNotFoundException, SQLException {
		return UserService.addUser(newUser);
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAll() throws ClassNotFoundException, SQLException {
		return UserService.getAll();
	}
}
