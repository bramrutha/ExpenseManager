package com.expense.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.expense.entity.Expense;
import com.expense.exception.DataBaseException;
import com.expense.database.DataBaseConnection;

public class ExpenseService extends UserService {

	private Connection dbConnection = null;

	private PreparedStatement preparedStatement = null;

	private static final String INSERT_QUERY = "INSERT INTO Expense" + "(id,userId,name,category,amount,date) VALUES"
			+ "(?,?,?,?,?,?)";

	private static final String SELECT_QUERY = "SELECT * FROM EXPENSE WHERE USERID = ?";

	private static final String UPDATE_QUERY = "UPDATE Expense SET name = ?,category =?,amount = ?,date = ?"
			+ "WHERE USERID = ? AND ID =?";

	private static final String DELETE_QUERY = "DELETE FROM EXPENSE WHERE ID = ?";

	public Expense addExpense(int userId, Expense newExpense) throws DataBaseException {

		// displayName(newExpense.getUserName());

		try {
			dbConnection = DataBaseConnection.getConnection();
			preparedStatement = dbConnection.prepareStatement(INSERT_QUERY);
			preparedStatement.setInt(1, newExpense.getexpenseId());
			preparedStatement.setInt(2, newExpense.getUserId());
			preparedStatement.setString(3, newExpense.getName());
			preparedStatement.setString(4, newExpense.getCategory());
			preparedStatement.setDouble(5, newExpense.getAmount());
			preparedStatement.setString(6, newExpense.getDate());
			preparedStatement.executeUpdate();

			System.out.println("Record is inserted into EXPENSE table!");

		} catch (SQLException exception) {
			throw new DataBaseException("Exception while Expense Insert", exception.getErrorCode());
		}

		return newExpense;
	}

	public List<Expense> getAll(int userId) throws DataBaseException {

		List<Expense> expenseList = new ArrayList<Expense>();

		try {
			dbConnection = DataBaseConnection.getConnection();
			preparedStatement = dbConnection.prepareStatement(SELECT_QUERY);
			preparedStatement.setInt(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Expense expense = new Expense();
				expense.setexpenseId(resultSet.getInt("ID"));
				expense.setUserId(resultSet.getInt("USERID"));
				expense.setName(resultSet.getString("NAME"));
				expense.setCategory(resultSet.getString("CATEGORY"));
				expense.setAmount(resultSet.getDouble("AMOUNT"));
				expense.setDate(resultSet.getString("DATE"));
				expenseList.add(expense);
			}
		} catch (SQLException exception) {
			throw new DataBaseException("Exception while Expense Select All", exception.getErrorCode());
		}
		return expenseList;
	}

	public Map<String, Double> getDayCatExpense(int userId, String fromDate, String toDate) throws DataBaseException {

		Map<String, Double> categoryMap = new HashMap<String, Double>();
		List<Expense> allList = new ArrayList<Expense>();
		Date fromDateParsed = null;
		Date toDateParsed = null;
		String category = null;

		try {

			if (fromDate != null) {
				fromDateParsed = new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
			}

			if (toDate != null) {
				toDateParsed = new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
			}

			allList = getAll(userId);

			for (Expense expense : allList) {
				Date expDate = new SimpleDateFormat("yyyy-MM-dd").parse(expense.getDate());

				if (toDate != null) {
					if (((expDate.equals(fromDateParsed)) || (expDate.after(fromDateParsed)))
							&& ((expDate.equals(toDateParsed)) || (expDate.before(toDateParsed)))) {

						category = expense.getCategory().toUpperCase();
						categoryMap = getCategoryExpenses(categoryMap, category, expense);
					}
				} else {
					if (expDate.equals(fromDateParsed)) {
						category = expense.getCategory().toUpperCase();
						categoryMap = getCategoryExpenses(categoryMap, category, expense);
					}
				}
			}

		} catch (ParseException exception) {
			exception.printStackTrace();
		}
		return categoryMap;
	}

	private Map<String, Double> getCategoryExpenses(Map<String, Double> categoryMap, String category, Expense expense) {

		if (categoryMap.get(category) != null) {
			categoryMap.put(category.toUpperCase(), categoryMap.get(category) + expense.getAmount());
			return categoryMap;
		}
		categoryMap.put(category.toUpperCase(), expense.getAmount());
		return categoryMap;
	}

	public List<Expense> getRangeExpense(int userId, String fromDate, String toDate) throws DataBaseException {

		List<Expense> dateList = new ArrayList<Expense>();
		List<Expense> allList = new ArrayList<Expense>();

		Date fromDateParsed = null;
		Date toDateParsed = null;

		try {
			if (fromDate != null) {
				fromDateParsed = new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
			}

			if (toDate != null) {
				toDateParsed = new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
			}

			allList = getAll(userId);

			for (Expense expense : allList) {
				Date expenseDate = new SimpleDateFormat("yyyy-MM-dd").parse(expense.getDate());

				if (toDate != null) {
					if (((expenseDate.equals(fromDateParsed)) || (expenseDate.after(fromDateParsed)))
							&& ((expenseDate.equals(toDateParsed)) || (expenseDate.before(toDateParsed))))

						dateList.add(expense);
				} else {
					if (expenseDate.equals(fromDateParsed))
						dateList.add(expense);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateList;
	}

	public Expense update(int userId, Expense expense) throws DataBaseException {

		try {

			dbConnection = DataBaseConnection.getConnection();
			preparedStatement = dbConnection.prepareStatement(UPDATE_QUERY);
			preparedStatement.setString(1, expense.getName());
			preparedStatement.setString(2, expense.getCategory());
			preparedStatement.setDouble(3, expense.getAmount());
			preparedStatement.setString(4, expense.getDate());
			preparedStatement.setInt(5, expense.getUserId());
			preparedStatement.setInt(6, expense.getexpenseId());

			preparedStatement.executeUpdate();

			System.out.println("Record is updated into EXPENSE table!");
		} catch (SQLException exception) {
			throw new DataBaseException("Exception while Expense Update", exception.getErrorCode());
		}
		return expense;

	}

	public void delete(int expenseId) throws DataBaseException {

		try {

			dbConnection = DataBaseConnection.getConnection();
			preparedStatement = dbConnection.prepareStatement(DELETE_QUERY);
			preparedStatement.setInt(1, expenseId);

			preparedStatement.executeUpdate();

			System.out.println("Record is deleted from EXPENSE table!");
		} catch (SQLException exception) {
			throw new DataBaseException("Exception while Expense Delete", exception.getErrorCode());
		}

	}

}
