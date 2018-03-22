package com.expense.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.expense.entity.Expense;
import com.expense.database.DataBaseConnection;



public class ExpenseService {	
	
	public static Expense addExpense(int userId,Expense newExpense) throws SQLException, ClassNotFoundException {
	
    Connection dbConnection = null;
	PreparedStatement preparedStatement = null;
		
	String insertSql = "INSERT INTO Expense"
		              + "(id,userId,name,category,amount,date) VALUES"
	                  + "(?,?,?,?)";
	try {
		
		dbConnection = DataBaseConnection.getConnection();	
		preparedStatement = dbConnection.prepareStatement(insertSql);
		preparedStatement.setInt(1, newExpense.getexpenseId());
		preparedStatement.setInt(2, newExpense.getUserId());
		preparedStatement.setString(3, newExpense.getName());
		preparedStatement.setString(4, newExpense.getCategory());
		preparedStatement.setString(5, newExpense.getName());
		preparedStatement.setDouble(6, newExpense.getAmount());
		preparedStatement.setString(7, newExpense.getDate());
		
		preparedStatement.executeUpdate();
		
		System.out.println("Record is inserted into DBUSER table!");
	}
	   catch(SQLException e) {
		   System.out.println(e.getMessage()); 
	   }
	  finally {
		  if (preparedStatement != null) {
			  preparedStatement.close();
	      }
		  if (dbConnection != null){
		      dbConnection.close();
	      }
	  }
	  return newExpense;
       
	}
}	
	
	
	
	
	
	
	
	
//	private static Map<Long,List<Expense>> expenseMap = new HashMap<Long,List<Expense>>();
	
	
	

	/*public static Expense addExpense(long userId,Expense newExpense) {
		

			 if (expenseMap.get(userId)== null) {
			    expenseMap.put(userId,new ArrayList<Expense>());
			 }
			 expenseMap.get(userId).add(newExpense);
			 return newExpense;

	}

	public static List<Expense> getAll(long userId) {
		return expenseMap.get(userId);
	}

	public static Expense update(long userId,Expense expense) {
		// TODO Auto-generated method stub
		
		
		 expenseMap.put(userId(), exp);	
		 return expenseMap.get(exp.getUserId());
		}	
		else
			 return exp;
		
	}

	public static Expense getById(long gid) {
		return expenseMap.get(gid);

	}
	
	public static Expense getByuserId(long gid) {
		return expenseMap.get(gid);

	}

	public static void delete(long id) {
		// TODO Auto-generated method stub
		expenseMap.remove(id);
	}
	
	

	public static boolean validateDate(String strdate) {

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

	public static List<Expense> getByuserId(long gid) {
		// TODO Auto-generated method stub
		Collection<Expense> c = expenseMap.values();
		Map<Long,List<Expense>> expenseList = new HashMap<Long,List<Expense>>();
		expenseList.addAll(c);
		return expenseList;
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
