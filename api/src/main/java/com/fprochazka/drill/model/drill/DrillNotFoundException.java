package com.fprochazka.drill.model.drill;

public class DrillNotFoundException extends Exception
{
	public DrillNotFoundException()
	{
	}

	public DrillNotFoundException(String message)
	{
		super(message);
	}

	public DrillNotFoundException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
