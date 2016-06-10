package com.fprochazka.drill.model.exceptions;

/**
 * Created by Michaela Bamburová on 11.06.2016.
 */
public class NotUniqueException extends Exception
{
	public NotUniqueException()
	{
	}

	public NotUniqueException(String message)
	{
		super(message);
	}

	public NotUniqueException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
