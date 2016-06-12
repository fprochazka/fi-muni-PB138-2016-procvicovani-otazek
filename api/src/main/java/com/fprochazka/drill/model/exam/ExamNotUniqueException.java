package com.fprochazka.drill.model.exam;

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
