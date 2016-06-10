package com.fprochazka.drill.api.exam;

import com.sun.istack.internal.NotNull;

import java.util.UUID;

/**
 * Created by viki on 09.06.16.
 */
public class UpdateExamRequest
{
	@NotNull
	private UUID questionId;

	@NotNull
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
