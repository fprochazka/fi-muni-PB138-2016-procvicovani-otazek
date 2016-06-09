package com.fprochazka.drill.api.question.answer;

/**
 * Created by viki on 09.06.16.
 */
public class CreateAnswerRequest {

	private String text;
	private Boolean correct;

	public CreateAnswerRequest(String test, Boolean correct) {
		this.text = test;
		this.correct = correct;
	}

	public CreateAnswerRequest() {
	}

	public String getText() {
		return text;
	}

	public Boolean isCorrect() {
		return correct;
	}
}
