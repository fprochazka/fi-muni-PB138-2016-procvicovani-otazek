package com.fprochazka.drill.api.question;

import com.fprochazka.drill.api.question.answer.UpdateAnswerRequest;

import java.util.Collection;

/**
 * Created by viki on 09.06.16.
 */
public class UpdateQuestionRequest {

	private String title;
	private Collection<UpdateAnswerRequest> answers;

	public UpdateQuestionRequest(String title, Collection<UpdateAnswerRequest> answers ) {
		this.title = title;
		this.answers = answers;
	}
	public UpdateQuestionRequest() {}

	public String getTitle() {
		return title;
	}

	public Collection<UpdateAnswerRequest> getAnswers() {
		return answers;
	}
}
