package com.ims.model.exception;

public class InsufficientStockException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsufficientStockException(int requested, int available) {
        super("Cannot remove " + requested + " units. Current stock is " + available);
    }
}