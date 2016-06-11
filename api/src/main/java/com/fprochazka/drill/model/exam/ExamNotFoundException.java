package com.fprochazka.drill.model.exam;

/**
 * Created by viki on 11.06.16.
 */
public class ExamNotFoundException extends Exception
{
	public ExamNotFoundException()
	{
	}

	public ExamNotFoundException(String message)
	{
		super(message);
	}

	public ExamNotFoundException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
