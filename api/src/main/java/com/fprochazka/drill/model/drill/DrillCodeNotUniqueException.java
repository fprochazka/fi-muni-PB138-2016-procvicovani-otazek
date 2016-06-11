package com.fprochazka.drill.model.drill;


/**
 * Created by viki on 11.06.16.
 */
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
