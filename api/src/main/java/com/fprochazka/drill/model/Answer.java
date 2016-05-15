package com.fprochazka.drill.model;


import org.springframework.data.annotation.Id;

/**
 * Created by Michaela Bamburová on 13.05.2016.
 */

/*
 *  is used to denote the collection in which the data will be persisted.
 *  If it is not mentioned, the data is saved in the collection which has the same name as the Entity Class Name.
@Document(collection="answer")
*/
public class Answer {
	@Id
	private String id;
	private boolean correct;
	private String text;

	public Answer() {}

	public Answer(boolean correct, String text) {
		this.correct = correct;
		this.text = text;
	}

	public boolean isCorrect() {
		return correct;
	}

	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		return String.format(
			"Answer[id=%s, firstName='%s', lastName='%s']",
			id, correct, text);
	}

}

