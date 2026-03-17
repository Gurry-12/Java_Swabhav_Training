package com.gurpreet.comparator.models;

public class Transaction {
	
	private int id;
	private double amount;
	
	public Transaction(int id, double amount) {
		
		if(id < 0) {
			throw new IllegalArgumentException("Id can't be negative");
		}
		
		if(amount < 0) {
			throw new IllegalArgumentException("Amount can't be negative");
		}
		this.id = id;
		this.amount = amount;
	}
	
	public int getId() {
		return id;
	}
	
	public double getAmount() {
		return amount;
	}
}
