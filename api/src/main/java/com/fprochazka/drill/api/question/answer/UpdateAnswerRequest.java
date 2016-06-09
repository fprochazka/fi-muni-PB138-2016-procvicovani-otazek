package com.fprochazka.drill.api.question.answer;

/**
 * Created by viki on 09.06.16.
 */
public class UpdateAnswerRequest {
	private String text;
	private Boolean correct;

	public UpdateAnswerRequest(String text, Boolean correct) {
		this.text = text;
		this.correct = correct;
	}

	public UpdateAnswerRequest() {
	}

	public String getText() {
		return text;
	}

	public Boolean isCorrect() {
		return correct;
	}
}
