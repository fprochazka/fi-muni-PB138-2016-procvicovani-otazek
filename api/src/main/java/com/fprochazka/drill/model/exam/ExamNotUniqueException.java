package com.fprochazka.drill.model.exam;

/**
 * Created by viki on 11.06.16.
 */
public class ExamNotUniqueException extends Exception
{
	public ExamNotUniqueException()
	{
	}

	public ExamNotUniqueException(String message)
	{
		super(message);
	}

	public ExamNotUniqueException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
