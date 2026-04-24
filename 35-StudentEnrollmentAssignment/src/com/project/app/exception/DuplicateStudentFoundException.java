package com.project.app.exception;

public class DuplicateStudentFoundException extends StudentEnrollmentException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateStudentFoundException(String message) {
		super(message);
	}

}
