package com.fprochazka.drill.api.exam;

import com.fprochazka.drill.model.drill.question.Question;

import java.util.UUID;

public class QuestionStatistics
{

	private UUID questionId;
	private int correct;
	private int wrong;

	public QuestionStatistics()
	{
	}

	public QuestionStatistics(UUID questionId, int correct, int wrong)
	{

		this.questionId = questionId;
		this.correct = correct;
		this.wrong = wrong;
	}

	public UUID getQuestionId()
	{
		return questionId;
	}

	public int getCorrect()
	{
		return correct;
	}

	public int getWrong()
	{
		return wrong;
	}
}
