package com.fprochazka.drill.model.drill;

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

	public String getText()
	{
		return text;
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

