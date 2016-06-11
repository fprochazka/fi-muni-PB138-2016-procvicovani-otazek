package com.fprochazka.drill.model.student;

/**
 * Created by viki on 11.06.16.
 */
public class StudentEmailNotUniqueException extends Exception
{
	public StudentEmailNotUniqueException()
	{
	}

	public StudentEmailNotUniqueException(String message)
	{
		super(message);
	}

	public StudentEmailNotUniqueException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
