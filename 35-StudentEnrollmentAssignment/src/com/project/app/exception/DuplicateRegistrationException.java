package com.project.app.exception;

public class DuplicateRegistrationException extends StudentEnrollmentException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateRegistrationException(String message) {
		super(message);
	}

}
