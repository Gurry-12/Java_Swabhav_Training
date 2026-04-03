package com.ims.model.exception;

public class ProductNotFoundException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductNotFoundException(int id) {
        super("Product not found with ID: " + id);
    }
}