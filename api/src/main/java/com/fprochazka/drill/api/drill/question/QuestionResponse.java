package com.fprochazka.drill.api.drill.question;

import com.fprochazka.drill.api.drill.question.answer.AnswerResponse;

import java.util.Collection;
import java.util.UUID;

public class QuestionResponse
{
	private UUID id;
	private String text;
	private Collection<AnswerResponse> answers;

	public QuestionResponse(UUID id, String text, Collection<AnswerResponse> answers)
	{
		this.id = id;
		this.text = text;
		this.answers = answers;
	}

	public QuestionResponse()
	{
	}

	public UUID getId()
	{
		return id;
	}

	public String getText()
	{
		return text;
	}

	public Collection<AnswerResponse> getAnswers()
	{
		return answers;
	}

}
