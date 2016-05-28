package com.fprochazka.drill.model.exam;

import com.fprochazka.drill.model.drill.Question;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Document
@TypeAlias("student_exam_question")
public class StudentExamQuestion {

	@Id
	private final UUID id;
	private final Question question;
	private final StudentExam exam;
	private final int correct;
	private final int wrong;

	public StudentExamQuestion(Question question, StudentExam exam, int correct, int wrong) {
		this.id = UUID.randomUUID();
		this.question = question;
		this.exam = exam;
		this.correct = correct;
		this.wrong = wrong;
	}

	public UUID getId() {
		return id;
	}

	public Question getQuestion() {
		return question;
	}

	public StudentExam getExam() {
		return exam;
	}

	public int getCorrect() {
		return correct;
	}

	public int getWrong() {
		return wrong;
	}

	@Override
	public String toString() {
		return "StudentExamQuestion{" +
			"id=" + id +
			", question=" + question +
			", exam=" + exam +
			", correct=" + correct +
			", wrong=" + wrong +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		StudentExamQuestion that = (StudentExamQuestion) o;

		return id != null ? id.equals(that.id) : that.id == null;

	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
