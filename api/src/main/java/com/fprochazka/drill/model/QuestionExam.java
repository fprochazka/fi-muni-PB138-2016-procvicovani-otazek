package com.fprochazka.drill.model;

import java.util.UUID;

/**
 * Created by Michaela Bamburová on 13.05.2016.
 */
public class QuestionExam {

	private final UUID id;
	private final UUID questionId;
	private final int numCorrect;
	private final int numWrong;

	public QuestionExam(UUID questionId, int numCorrect, int numWrong) {
		this.id = UUID.randomUUID();
		this.questionId = questionId;
		this.numCorrect = numCorrect;
		this.numWrong = numWrong;
	}

	public UUID getId() {
		return id;
	}

	public int getNumWrong() {
		return numWrong;
	}

	public int getNumCorrect() {
		return numCorrect;
	}

	public UUID getQuestionId() {
		return questionId;
	}

	@Override
	public String toString() {
		return "QuestionExam{" +
			"id='" + id + '\'' +
			", questionId='" + questionId + '\'' +
			", numCorrect=" + numCorrect +
			", numWrong=" + numWrong +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		QuestionExam that = (QuestionExam) o;

		return id != null ? id.equals(that.id) : that.id == null;

	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
