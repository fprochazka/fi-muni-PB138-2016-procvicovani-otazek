package com.fprochazka.drill.model.drill.question;

public class Answer
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
	public String toString() {
		return "Answer{" +
			"correct=" + correct +
			", text='" + text + '\'' +
			'}';
	}
}

