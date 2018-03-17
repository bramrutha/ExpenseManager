package com.expense.entity;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Expense {
	
	private long id;
	private String name;
	private String category;
	private double Amount;
	
	public Expense() {}
	/**
	 * @param id
	 * @param name
	 * @param category
	 * @param amount
	 */
	public Expense(long id, String name, String category, double amount) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		Amount = amount;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
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
		return Amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		Amount = amount;
	}
	
	
	
	
}
