package com.fprochazka.drill.model.drill.answer;

import com.fprochazka.drill.model.Identified;

public class Answer extends Identified
{
	private boolean correct;
	private String text;

	public Answer(boolean correct, String text)
	{
		super();
		this.correct = correct;
		this.text = text;
	}

	public boolean isCorrect()
	{
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString()
	{
		return String.format(
			"Answer[id=%s, firstName='%s', lastName='%s']",
			getId(), correct, text
		);
	}

}

