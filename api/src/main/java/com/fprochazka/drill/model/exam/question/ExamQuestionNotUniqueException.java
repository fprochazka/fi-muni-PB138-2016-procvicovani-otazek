package com.fprochazka.drill.model.exam.question;

public class ExamQuestionNotUniqueException extends Exception
{
	public ExamQuestionNotUniqueException()
	{
	}

	public ExamQuestionNotUniqueException(String message)
	{
		super(message);
	}

	public ExamQuestionNotUniqueException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
