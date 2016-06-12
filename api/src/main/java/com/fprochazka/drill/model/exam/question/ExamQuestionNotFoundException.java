package com.fprochazka.drill.model.exam.question;

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
