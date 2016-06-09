package com.fprochazka.drill.api.question.answer;

/**
 * Created by viki on 09.06.16.
 */
public class AnswerResponse {

	private String text;
	private Boolean correct;

	public AnswerResponse( String text, Boolean correct ) {
		this.text = text;
		this.correct = correct;
	}

	public String getText() {
		return text;
	}

	public Boolean getCorrect() {
		return correct;
	}
}
