package com.expense.entity;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown=true)
public class Expense extends User{
	
	private int expenseId;
	private String name;
	private String category;
	private double amount;
	private String date;
	
	public Expense() {}
	/**
	 * @param expenseId
	 * @param name
	 * @param category
	 * @param amount
	 */
	public Expense(int expenseId, String name, String category, double amount,String date) {
		super();
		this.expenseId = expenseId;
		this.name = name;
		this.category = category;
		this.amount = amount;
		this.date = date;
		
	}
	
	
	/**
	 * @return the id
	 */
	public int getexpenseId() {
		return expenseId;
	}
	/**
	 * @param id the id to set
	 */
	public void setexpenseId(int expenseId) {
		this.expenseId = expenseId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}	
}
