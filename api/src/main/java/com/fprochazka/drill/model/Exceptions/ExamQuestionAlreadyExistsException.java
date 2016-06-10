package com.fprochazka.drill.model.exceptions;

/**
 * Created by Michaela Bamburová on 10.06.2016.
 */
public class ExamQuestionAlreadyExistsException extends Exception {

	public ExamQuestionAlreadyExistsException() {
	}

	public ExamQuestionAlreadyExistsException(String message) {
		super(message);
	}

	public ExamQuestionAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}
}
