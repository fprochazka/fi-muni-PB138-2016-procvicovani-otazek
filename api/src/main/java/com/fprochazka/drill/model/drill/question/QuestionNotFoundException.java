package com.fprochazka.drill.model.drill.question;

public class QuestionNotFoundException extends Exception
{
	public QuestionNotFoundException()
	{
	}

	public QuestionNotFoundException(String message)
	{
		super(message);
	}

	public QuestionNotFoundException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
