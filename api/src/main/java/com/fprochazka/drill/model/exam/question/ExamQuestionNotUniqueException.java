package com.fprochazka.drill.model.exam.question;

/**
 * Created by viki on 11.06.16.
 */
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
