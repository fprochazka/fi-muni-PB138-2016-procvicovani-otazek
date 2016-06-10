package com.fprochazka.drill.model.Exceptions;

/**
 * Created by Michaela Bamburová on 10.06.2016.
 */
public class QuestionAlreadyExistsException extends Exception {

	public QuestionAlreadyExistsException() {
	}

	public QuestionAlreadyExistsException(String message) {
		super(message);
	}

	public QuestionAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}
}
