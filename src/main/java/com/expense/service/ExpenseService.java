package com.expense.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.expense.entity.Expense;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import com.expense.database.DataBaseConnection;



public class ExpenseService {	
	
	private static List<Expense> allList = new ArrayList<Expense>();
		
	public static Expense addExpense(int userId,Expense newExpense) throws SQLException, ClassNotFoundException {
	
	Connection dbConnection = null;
	PreparedStatement preparedStatement = null;
		
	String insertSql = "INSERT INTO Expense"
		              + "(id,userId,name,category,amount,date) VALUES"
	                  + "(?,?,?,?,?,?)";
	try {
		
		dbConnection = DataBaseConnection.getConnection();	
		preparedStatement = dbConnection.prepareStatement(insertSql);
		preparedStatement.setInt(1, newExpense.getexpenseId());
		preparedStatement.setInt(2, newExpense.getUserId());
		preparedStatement.setString(3, newExpense.getName());
		preparedStatement.setString(4, newExpense.getCategory());
		preparedStatement.setDouble(5, newExpense.getAmount());
		preparedStatement.setString(6, newExpense.getDate());
		
		preparedStatement.executeUpdate();
		
		System.out.println("Record is inserted into EXPENSE table!");
	}
	   catch(SQLException e) {
		   System.out.println(e.getMessage()); 
	   }
	  return newExpense;
       
	}

	public static List<Expense> getAll(int userId) throws SQLException, ClassNotFoundException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		List<Expense> expenseList = new ArrayList<Expense>();

		String selectSql = "SELECT * FROM EXPENSE WHERE USERID = ?";
		

		try {

			dbConnection = DataBaseConnection.getConnection();
			preparedStatement = dbConnection.prepareStatement(selectSql);
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();


			while (rs.next()) {
				Expense s1 = new Expense();
				s1.setexpenseId(rs.getInt("ID"));
				s1.setUserId(rs.getInt("USERID"));
				s1.setName(rs.getString("NAME"));
				s1.setCategory(rs.getString("CATEGORY"));
				s1.setAmount(rs.getDouble("AMOUNT"));
				s1.setDate(rs.getString("DATE"));
				expenseList.add(s1);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		return expenseList;
	}
	
	
	public static Map<String, Double> getDayCatExpense(int userId, String fromDate, String toDate) throws ClassNotFoundException, SQLException, java.text.ParseException {
		
		Map<String,Double> categoryMap = new HashMap<String,Double>();
		
		Date fromDate1 = null;
		Date toDate1 = null;
		
		allList = getAll(userId);
		Double foodExpense = 0.00;
		Double shopExpense = 0.00;
		Double healthExpense = 0.00;
		Double fuelExpense = 0.00;
		Double enterExpense = 0.00;
		Double groceryExpense = 0.00;
		Double otherExpense = 0.00;
		
		
		if (fromDate != null) {
		   fromDate1 = new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
		   Calendar cal1 = Calendar.getInstance();
		   cal1.setTime(fromDate1);
		}   
		
		if(toDate != null) {
		   toDate1 = new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
		   Calendar cal2 = Calendar.getInstance();
		   cal2.setTime(toDate1);
		}

		
	    for(Expense exp : allList)
		{
	    	Date expDate = new SimpleDateFormat("yyyy-MM-dd").parse(exp.getDate());	
		    Calendar cal3 = Calendar.getInstance();
		    cal3.setTime(expDate);
		       
		    if (toDate != null) {
		      if (((expDate.equals(fromDate1)) || (expDate.after(fromDate1)))
		    	   	    &&
		    	     ((expDate.equals(toDate1)) || (expDate.before(toDate1)))) {	       
	             if((exp.getCategory()).equalsIgnoreCase("FOOD"))
	        	   foodExpense = foodExpense + exp.getAmount();
	             else if((exp.getCategory()).equalsIgnoreCase("SHOPPING"))
	        	   shopExpense = shopExpense + exp.getAmount();
	             else if((exp.getCategory()).equalsIgnoreCase("HEALTH"))
	        	   healthExpense = healthExpense + exp.getAmount();
	             else if((exp.getCategory()).equalsIgnoreCase("FUEL"))
	        	   fuelExpense = fuelExpense + exp.getAmount();
	             else if((exp.getCategory()).equalsIgnoreCase("ENTERTAINMET"))
	        	   enterExpense = enterExpense + exp.getAmount();
	             else if((exp.getCategory()).equalsIgnoreCase("GROCERY"))
	        	   groceryExpense = groceryExpense + exp.getAmount();
	             else
	        	   otherExpense = otherExpense + exp.getAmount();
		         }
		    }  
		    else {
		       if (expDate.equals(fromDate1)) {
		    	   if((exp.getCategory()).equalsIgnoreCase("FOOD"))
		        	   foodExpense = foodExpense + exp.getAmount();
		             else if((exp.getCategory()).equalsIgnoreCase("SHOPPING"))
		        	   shopExpense = shopExpense + exp.getAmount();
		             else if((exp.getCategory()).equalsIgnoreCase("HEALTH"))
		        	   healthExpense = healthExpense + exp.getAmount();
		             else if((exp.getCategory()).equalsIgnoreCase("FUEL"))
		        	   fuelExpense = fuelExpense + exp.getAmount();
		             else if((exp.getCategory()).equalsIgnoreCase("ENTERTAINMET"))
		        	   enterExpense = enterExpense + exp.getAmount();
		             else if((exp.getCategory()).equalsIgnoreCase("GROCERY"))
		        	   groceryExpense = groceryExpense + exp.getAmount();
		             else
		        	   otherExpense = otherExpense + exp.getAmount();  
		       }
		    		
		   }    
		}
	    categoryMap.put("FOOD", foodExpense);
	    categoryMap.put("SHOPPING", shopExpense);
	    categoryMap.put("HEALTH", healthExpense);
	    categoryMap.put("FUEL", fuelExpense);
	    categoryMap.put("ENTERTAINMENT", enterExpense);
	    categoryMap.put("GROCERY", groceryExpense);
	    categoryMap.put("OTHER", otherExpense);
		return categoryMap;
	}

	public static List<Expense> getRangeExpense(int userId, String fromDate, String toDate) throws ClassNotFoundException, SQLException, java.text.ParseException {
		List<Expense> dateList = new ArrayList<Expense>();
		Date fromDate1 = null;
		Date toDate1 = null;
		
		
		
		if (fromDate != null) {
		   fromDate1 = new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
		   Calendar cal1 = Calendar.getInstance();
		   cal1.setTime(fromDate1);
		}   
		
		if(toDate != null) {
		   toDate1 = new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
		   Calendar cal2 = Calendar.getInstance();
		   cal2.setTime(toDate1);
		}
        
		allList = getAll(userId);
	    for(Expense exp : allList)
		{
	       Date expDate = new SimpleDateFormat("yyyy-MM-dd").parse(exp.getDate());	
	       Calendar cal3 = Calendar.getInstance();
	       cal3.setTime(expDate);
	       
	       if (toDate != null) {
	         if (((expDate.equals(fromDate1)) || (expDate.after(fromDate1)))
	    	   	    &&
	    	     ((expDate.equals(toDate1)) || (expDate.before(toDate1))))	    
	              
	        	 dateList.add(exp);
	       }
	       else {
	    	 if (expDate.equals(fromDate1))
	    		 dateList.add(exp);
	       }
        } 
		
		return dateList;
	}
	
	public static Expense update(int userId,Expense expense) throws ClassNotFoundException {
		
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSql = "UPDATE Expense SET name = ?,category =?,amount = ?,date = ?"
			              + "WHERE USERID = ? AND ID =?";
		                  
		try {
			
			dbConnection = DataBaseConnection.getConnection();	
			preparedStatement = dbConnection.prepareStatement(updateSql);
	        preparedStatement.setString(1, expense.getName());
			preparedStatement.setString(2, expense.getCategory());
			preparedStatement.setDouble(3, expense.getAmount());
			preparedStatement.setString(4, expense.getDate());
			preparedStatement.setInt(5, expense.getUserId());
			preparedStatement.setInt(6, expense.getexpenseId());
			
			preparedStatement.executeUpdate();
			
			System.out.println("Record is updated into EXPENSE table!");
		}
		   catch(SQLException e) {
			   System.out.println(e.getMessage()); 
		   }
		  return expense;
	       
		}

	public static void delete(int expenseId) throws ClassNotFoundException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		
		String deleteSql = "DELETE FROM EXPENSE WHERE ID = ?";
			             		                  
		try {
			
			dbConnection = DataBaseConnection.getConnection();
			preparedStatement = dbConnection.prepareStatement(deleteSql);
			preparedStatement.setInt(1, expenseId);
			
			preparedStatement.executeUpdate();
			
			System.out.println("Record is deleted from EXPENSE table!");
		}
		   catch(SQLException e) {
			   System.out.println(e.getMessage()); 
		   }
		 	
	}

		
}	
	 













/*public static boolean validateDate(String strdate) throws java.text.ParseException {

		SimpleDateFormat sdfrmt = new SimpleDateFormat("yyyy-MM-dd");
		sdfrmt.setLenient(false);
		try {
			Date javaDate = sdfrmt.parse(strdate);
			System.out.println("strdate : " + strdate);
			System.out.println("Date : " + sdfrmt.parse(strdate));

		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}	
	
	
	
	
	
	
	
	
}


//boolean valDate = validateDate(newExpense.getDate());
//List<Expense> list = new LinkedList<Expense>();
//list.add(newExpense);
//if (valDate) {
//	}	
//	else
//		 return list;
 * 
 */
