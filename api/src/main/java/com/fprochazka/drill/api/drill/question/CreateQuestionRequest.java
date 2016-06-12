package com.fprochazka.drill.api.drill.question;

import com.fprochazka.drill.api.drill.question.answer.CreateAnswerRequest;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Collection;

public class CreateQuestionRequest
{
	@NotEmpty
	private String text;

	@NotEmpty
	private Collection<CreateAnswerRequest> answers;

	public CreateQuestionRequest(String title, Collection<CreateAnswerRequest> answers)
	{
		this.text = title;
		this.answers = answers;
	}

	public CreateQuestionRequest()
	{
	}

	public String getText()
	{
		return text;
	}

	public Collection<CreateAnswerRequest> getAnswers()
	{
		return answers;
	}
}
