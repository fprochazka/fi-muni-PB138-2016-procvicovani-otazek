package com.fprochazka.drill.model.exam.question;

/**
 * Created by viki on 11.06.16.
 */
public class ExamQuestionNotFoundException extends Exception
{
	public ExamQuestionNotFoundException()
	{
	}

	public ExamQuestionNotFoundException(String message)
	{
		super(message);
	}

	public ExamQuestionNotFoundException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
