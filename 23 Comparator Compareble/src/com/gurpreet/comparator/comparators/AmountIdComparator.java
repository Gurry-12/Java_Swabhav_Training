package com.gurpreet.comparator.comparators;

import java.util.Comparator;

import com.gurpreet.comparator.models.Transaction;

public class AmountIdComparator implements Comparator<Transaction> {

	@Override
	public int compare(Transaction transaction1, Transaction transaction2) {

		int amountComparison = Double.compare(transaction2.getAmount(), transaction1.getAmount());
        if (amountComparison != 0) {
            return amountComparison;
        }

        // Secondary sort key: ID (ascending)
        return Long.compare(transaction1.getId(), transaction2.getId());

	}

//	private int getIdOfTransaction(Transaction transaction) {
//		return transaction.getId();
//	}
//
//	private double getAmountOfTransaction(Transaction transaction) {
//		return transaction.getAmount();
//	}

}
