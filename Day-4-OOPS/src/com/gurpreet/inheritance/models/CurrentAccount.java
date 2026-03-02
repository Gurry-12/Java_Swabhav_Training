package com.gurpreet.inheritance.models;

public class CurrentAccount extends Account {

	private final double overDraftLimit;
	
	public CurrentAccount(String accountHolderName, double balance, double overDraftLimit) {
		super(accountHolderName, balance);
		
		this.overDraftLimit = overDraftLimit;
		
	}
	
	@Override
	public void withdraw(double amount) {
		if (amount < 0) {
			System.out.println("Withdraw amount can not be negative.");
			return;
		}

		double availableAmount = super.getBalance() + overDraftLimit;
		if (availableAmount < amount ) {
			System.out.println("Your Amount exceeded the overDraft limit ");
			return;
		}
		
		

		super.withdraw(amount);
	}
	
}
