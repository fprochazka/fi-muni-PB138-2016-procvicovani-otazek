package com.fprochazka.drill.api.question;

import com.fprochazka.drill.api.question.answer.CreateAnswerRequest;
import com.fprochazka.drill.model.drill.question.Answer;

import java.util.Collection;

/**
 * Created by viki on 09.06.16.
 */
public class CreateQuestionRequest
{
	private String title;
	private Collection<CreateAnswerRequest> answers;

	public CreateQuestionRequest(String title, Collection<CreateAnswerRequest> answers)
	{
		this.title = title;
		this.answers = answers;
	}

	public CreateQuestionRequest()
	{
	}

	public String getTitle()
	{
		return title;
	}

	public Collection<CreateAnswerRequest> getAnswers()
	{
		return answers;
	}
}
