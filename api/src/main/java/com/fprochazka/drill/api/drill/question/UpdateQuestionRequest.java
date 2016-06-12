package com.fprochazka.drill.api.drill.question;

import com.fprochazka.drill.api.drill.question.answer.UpdateAnswerRequest;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Collection;

public class UpdateQuestionRequest
{
	@NotEmpty
	private String text;

	@NotEmpty
	private Collection<UpdateAnswerRequest> answers;

	public UpdateQuestionRequest(String title, Collection<UpdateAnswerRequest> answers)
	{
		this.text = title;
		this.answers = answers;
	}

	public UpdateQuestionRequest()
	{
	}

	public String getText()
	{
		return text;
	}

	public Collection<UpdateAnswerRequest> getAnswers()
	{
		return answers;
	}
}
