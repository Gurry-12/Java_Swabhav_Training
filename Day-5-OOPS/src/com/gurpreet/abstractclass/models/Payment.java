package com.gurpreet.abstractclass.models;

public abstract class Payment {

	protected double amount;
	protected double finalAmount;
	
	public Payment(double amount) {
		if(amount < 0) {
			System.out.println("The Amount cannot be negative");
			return;
		}
		
		this.amount = amount;
	}
	
	public boolean validateAmount() {
        return amount > 0;
    }
	
	public abstract void processPayment();
	
	public void generateReceipt() {
		System.out.println("---------------------------");
		System.out.println("      Receipt Generate     ");
		System.out.println("---------------------------");
		
		System.out.println("Inital Amount : " + amount);
		System.out.println("Final Amount : " + finalAmount);
	}
}
