package com.fprochazka.drill.model.student;

/**
 * Created by viki on 11.06.16.
 */
public class StudentUcoNotUniqueException extends Exception
{
	public StudentUcoNotUniqueException()
	{
	}

	public StudentUcoNotUniqueException(String message)
	{
		super(message);
	}

	public StudentUcoNotUniqueException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
