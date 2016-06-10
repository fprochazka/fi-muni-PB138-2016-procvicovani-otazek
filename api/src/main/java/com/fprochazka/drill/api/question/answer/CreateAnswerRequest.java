package com.fprochazka.drill.api.question.answer;

import com.sun.istack.internal.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by viki on 09.06.16.
 */
public class CreateAnswerRequest
{

	@NotNull
	@NotEmpty
	private String text;

	@NotNull
	private Boolean correct;

	public CreateAnswerRequest(String test, Boolean correct)
	{
		this.text = test;
		this.correct = correct;
	}

	public CreateAnswerRequest()
	{
	}

	public String getText()
	{
		return text;
	}

	public Boolean isCorrect()
	{
		return correct;
	}
}
