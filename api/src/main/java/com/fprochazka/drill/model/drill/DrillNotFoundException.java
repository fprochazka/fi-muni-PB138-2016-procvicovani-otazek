package com.fprochazka.drill.model.drill;

/**
 * Created by viki on 11.06.16.
 */
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
