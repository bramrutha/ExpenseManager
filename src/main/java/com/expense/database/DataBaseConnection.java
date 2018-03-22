	package com.expense.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConnection {

	private static Connection connection = null;

	public static Connection getConnection() throws ClassNotFoundException, SQLException {

		if (connection == null) {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:E:/chinook.db");
			System.out.println("Connected to expense database");
			return connection;
		}
		return connection;
	}

	public static void initializeDatabase() throws ClassNotFoundException, SQLException {

		String createUserTable = "CREATE TABLE IF NOT EXISTS USER (\n" 
				+ "	id integer PRIMARY KEY,\n" 
				+ "	name text\n"
				+ " );";
		String createExpenseTable = "CREATE TABLE IF NOT EXISTS EXPENSE (\n" 
				+ "	id integer PRIMARY KEY,\n"
				+ "	userId integer,\n" 
				+ "	name text,\n" 
				+ "	category text,\n"
				+ "	amount real,\n"
				+ " date text,\n" 
				+ " FOREIGN KEY(userId) REFERENCES USER(id)\n" + " );";

		Statement statement = getConnection().createStatement();
		statement.execute(createUserTable);
		statement.execute(createExpenseTable);
	}
}
