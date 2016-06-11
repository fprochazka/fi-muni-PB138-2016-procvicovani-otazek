package com.fprochazka.drill.model.drill.question;

/**
 * Created by viki on 11.06.16.
 */
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
