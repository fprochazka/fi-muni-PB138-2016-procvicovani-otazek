package com.fprochazka.drill.api.question.answer;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by viki on 09.06.16.
 */
public class UpdateAnswerRequest
{
	@NotEmpty
	private String text;

	@NotEmpty
	private Boolean correct;

	public UpdateAnswerRequest(String text, Boolean correct)
	{
		this.text = text;
		this.correct = correct;
	}

	public UpdateAnswerRequest()
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
