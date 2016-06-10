package com.fprochazka.drill.api.exam;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.UUID;

/**
 * Created by viki on 09.06.16.
 */
public class UpdateExamRequest
{
	@NotEmpty
	private UUID questionId;

	@NotEmpty
	private Boolean correct;


	public UpdateExamRequest(UUID questionId, Boolean correct)
	{
		this.questionId = questionId;
		this.correct = correct;
	}

	public UpdateExamRequest()
	{
	}

	public UUID getQuestionId()
	{
		return questionId;
	}

	public Boolean getCorrect()
	{
		return correct;
	}
}
