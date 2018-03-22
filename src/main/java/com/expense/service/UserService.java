package com.expense.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.expense.database.DataBaseConnection;
import com.expense.entity.User;

public class UserService {

	public static User addUser(User newUser) throws SQLException, ClassNotFoundException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertSql = "INSERT INTO User" + "(id,name) VALUES" + "(?,?)";
		try {

			dbConnection = DataBaseConnection.getConnection();
			preparedStatement = dbConnection.prepareStatement(insertSql);
			preparedStatement.setInt(1, newUser.getUserId());
			preparedStatement.setString(2, newUser.getUserName());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return newUser;
	}

	public static List<User> getAll() throws ClassNotFoundException, SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		List<User> userList = new ArrayList<User>();

		String selectSql = "SELECT ID,NAME FROM USER";

		try {

			dbConnection = DataBaseConnection.getConnection();
			preparedStatement = dbConnection.prepareStatement(selectSql);

			ResultSet rs = preparedStatement.executeQuery();


			while (rs.next()) {
				User s1 = new User();
				s1.setUserId(rs.getInt("ID"));
				s1.setUserName(rs.getString("NAME"));
				userList.add(s1);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return userList;

	}

}

/*
 * private static Map<Long,User > userMap = new HashMap<Long, User>();
 * 
 * public static User addUser(User newUser) {
 * userMap.put(newUser.getUserId(),newUser); return
 * userMap.get(newUser.getUserId()); }
 * 
 * public static User getById(long gid) { TODO Auto-generated method stub return
 * userMap.get(gid); } }
 */