package com.fprochazka.drill.model.exceptions;

/**
 * Created by Michaela Bamburová on 10.06.2016.
 */
public class DrillCodeNotUniqueException extends Exception {

	public DrillCodeNotUniqueException() {
	}

	public DrillCodeNotUniqueException(String message) {
		super(message);
	}

	public DrillCodeNotUniqueException(String message, Throwable cause) {
		super(message, cause);
	}
}
