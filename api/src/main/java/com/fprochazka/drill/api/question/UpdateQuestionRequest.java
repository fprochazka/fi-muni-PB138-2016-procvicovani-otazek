package com.fprochazka.drill.api.question;

import com.fprochazka.drill.api.question.answer.UpdateAnswerRequest;
import com.sun.istack.internal.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Collection;

/**
 * Created by viki on 09.06.16.
 */
public class UpdateQuestionRequest
{

	@NotNull
	@NotEmpty
	private String title;

	@NotNull
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
