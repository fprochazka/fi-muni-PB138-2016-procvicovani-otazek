package com.fprochazka.drill.api.question;

import com.fprochazka.drill.api.question.answer.UpdateAnswerRequest;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Collection;

public class UpdateQuestionRequest
{
	@NotEmpty
	private String title;

	@NotEmpty
	private Collection<UpdateAnswerRequest> answers;

	public UpdateQuestionRequest(String title, Collection<UpdateAnswerRequest> answers)
	{
		this.title = title;
		this.answers = answers;
	}

	public UpdateQuestionRequest()
	{
	}

	public String getTitle()
	{
		return title;
	}

	public Collection<UpdateAnswerRequest> getAnswers()
	{
		return answers;
	}
}
