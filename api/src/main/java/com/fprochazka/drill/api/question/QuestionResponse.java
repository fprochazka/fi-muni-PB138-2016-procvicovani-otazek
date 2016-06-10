package com.fprochazka.drill.api.question;

import com.fprochazka.drill.api.question.answer.AnswerResponse;

import java.util.Collection;
import java.util.UUID;

public class QuestionResponse
{
	private UUID id;
	private String title;
	private Collection<AnswerResponse> answers;

	public QuestionResponse( UUID id, String title, Collection<AnswerResponse> answers )
	{
		this.id = id;
		this.title = title;
		this.answers = answers;
	}

	public QuestionResponse() {}

	public UUID getId() { return id; }

	public String getTitle()
	{
		return title;
	}

	public Collection<AnswerResponse> getAnswers()
	{
		return answers;
	}

}
