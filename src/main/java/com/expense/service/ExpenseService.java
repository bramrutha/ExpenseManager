package com.expense.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.expense.entity.Expense;

public class ExpenseService {
	
	private static Map<Long,Expense> expenseMap= new HashMap<Long,Expense>();

	public static Expense addExpense(Expense newExpense) {
		expenseMap.put(newExpense.getId(),newExpense);
		return expenseMap.get(newExpense.getId());		
	}
	
	public static List<Expense> getExpenses() {
		return new ArrayList<Expense>(expenseMap.values());
	}
	

}
