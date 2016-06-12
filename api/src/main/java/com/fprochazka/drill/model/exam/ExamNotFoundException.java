package com.fprochazka.drill.model.exam;

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
