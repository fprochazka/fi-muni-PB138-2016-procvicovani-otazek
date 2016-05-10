package com.fprochazka.drill.api.question;

import java.util.Collection;

public class QuestionRequest
{

	private String text;

	private Collection<QuestionAnswerRequest> answers;

	public QuestionRequest(String text, Collection<QuestionAnswerRequest> answers)
	{
		this.text = text;
		this.answers = answers;
	}

	public String getText()
	{
		return text;
	}

	public Collection<QuestionAnswerRequest> getAnswers()
	{
		return answers;
	}

	class QuestionAnswerRequest
	{
		private String text;
		private boolean correct;

		QuestionAnswerRequest(String text, boolean correct)
		{
			this.text = text;
			this.correct = correct;
		}

		public String getText()
		{
			return text;
		}

		public boolean isCorrect()
		{
			return correct;
		}
	}

}
