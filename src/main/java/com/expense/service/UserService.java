package com.expense.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.expense.database.DataBaseConnection;
import com.expense.entity.User;
import com.expense.exception.DataBaseException;

public class UserService {

	private Connection dbConnection = null;

	private PreparedStatement preparedStatement = null;

	private static final String INSERT_QUERY = "INSERT INTO User" + "(id,name) VALUES" + "(?,?)";

	private static final String SELECT_QUERY = "SELECT ID,NAME FROM USER";

	public User addUser(User newUser) throws DataBaseException {

		try {
			dbConnection = DataBaseConnection.getConnection();
			preparedStatement = dbConnection.prepareStatement(INSERT_QUERY);
			preparedStatement.setInt(1, newUser.getUserId());
			preparedStatement.setString(2, newUser.getUserName());

			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			throw new DataBaseException("Exception while User Insert", exception.getErrorCode());
		}

		return newUser;
	}

	public List<User> getAll() throws DataBaseException {

		List<User> userList = new ArrayList<User>();
		try {

			dbConnection = DataBaseConnection.getConnection();
			preparedStatement = dbConnection.prepareStatement(SELECT_QUERY);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				User user = new User();
				user.setUserId(resultSet.getInt("ID"));
				user.setUserName(resultSet.getString("NAME"));
				userList.add(user);
			}
		} catch (SQLException exception) {
			throw new DataBaseException("Exception while User Select", exception.getErrorCode());
		}
		return userList;

	}

}
