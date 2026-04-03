package com.ims.model.exception;

public class DuplicateProductException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateProductException(String name) {
        super("Product already exists: '" + name + "'");
    }
}