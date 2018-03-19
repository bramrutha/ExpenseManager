package com.expense.service;


import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.expense.entity.Expense;

public class ExpenseService {
	
	private static Map<Long,Expense> expenseMap= new HashMap<Long,Expense>();

	public static Expense addExpense(Expense newExpense) {
		expenseMap.put(newExpense.getId(),newExpense);
		return expenseMap.get(newExpense.getId());	
		
		
	}

	public static List<Expense> getAll() {
		// TODO Auto-generated method stub
		Collection<Expense> c = expenseMap.values();
		List<Expense> expenseList = new LinkedList<Expense>();
		expenseList.addAll(c);
		return expenseList;
	}
	
	

	
	public static Expense update(Expense exp) {
		// TODO Auto-generated method stub
		 expenseMap.put(exp.getId(),exp);
		 return expenseMap.get(exp.getId());
		
	}

	public static Expense getById(long gid) {
		return expenseMap.get(gid);
		
	}

	public static void delete(long id) {
		// TODO Auto-generated method stub
		expenseMap.remove(id);
	}

	
}
