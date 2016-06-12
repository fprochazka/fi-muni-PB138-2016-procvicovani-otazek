package com.fprochazka.drill.model.drill;


public class DrillCodeNotUniqueException extends Exception
{
	public DrillCodeNotUniqueException()
	{
	}

	public DrillCodeNotUniqueException(String message)
	{
		super(message);
	}

	public DrillCodeNotUniqueException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
