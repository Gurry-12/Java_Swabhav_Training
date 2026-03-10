package com.gurpreet.inheritance.models;

public class SavingAccount extends Account {

	private final double minimumBalance;

	public SavingAccount(String accountHolderName, double balance, double minimumBalance) {
		super(accountHolderName, balance);

		this.minimumBalance = minimumBalance;
	}

	@Override
	public void withdraw(double amount) {
		if (amount < 0) {
			System.out.println("Withdraw amount can not be negative.");
			return;
		}
		
		double balanceAfter = super.getBalance() - amount;
		if (balanceAfter < minimumBalance) {
			System.out.println("Please maintain minimum balance " + minimumBalance);
			return;
		}
		
		if(amount > super.getBalance()) {
			System.out.println("Insufficient balance.");
			return;
		}

		super.withdraw(amount);
	}

}
