package com.fprochazka.drill.api.question.answer;

import org.hibernate.validator.constraints.NotEmpty;

public class CreateAnswerRequest
{

	@NotEmpty
	private String text;

	@NotEmpty
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
