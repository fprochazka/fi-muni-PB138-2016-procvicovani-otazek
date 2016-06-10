package com.fprochazka.drill.api.question;

import com.fprochazka.drill.api.question.answer.CreateAnswerRequest;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Collection;

/**
 * Created by viki on 09.06.16.
 */
public class CreateQuestionRequest
{
	@NotEmpty
	private String title;

	@NotEmpty
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
