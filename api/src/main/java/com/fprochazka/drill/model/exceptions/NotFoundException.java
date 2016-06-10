package com.fprochazka.drill.model.exceptions;

/**
 * Created by Michaela Bamburová on 10.06.2016.
 */
public class NotFoundException extends Exception
{
	public NotFoundException()
	{
	}

	public NotFoundException(String message)
	{
		super(message);
	}

	public NotFoundException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
