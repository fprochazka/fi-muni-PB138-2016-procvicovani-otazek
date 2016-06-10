package com.fprochazka.drill.model.exceptions;

/**
 * Created by Michaela Bamburová on 10.06.2016.
 */
public class ExamAlreadyExistsException extends Exception {

	public ExamAlreadyExistsException() {
	}

	public ExamAlreadyExistsException(String message) {
		super(message);
	}

	public ExamAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}
}
