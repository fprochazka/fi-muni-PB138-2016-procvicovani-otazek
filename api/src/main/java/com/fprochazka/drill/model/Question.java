package com.fprochazka.drill.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by Michaela Bamburová on 13.05.2016.
 */
@Document
public class Question {

	@Id
	@Field(value = "_id")
	private final UUID id;
	private final String title;

	//@DBRef is used to relate an existing entity to the current entity.
	// However, unlike the case with Relational Databases,
	// if we save the host entity it does not save the related entity. It has to be persisted separately.
	//@DBRef(db="answer")
	private final List<Answer> answers;
	private final Drill drill;


	//@PersistenceConstructor is used to mark the constructor
	// which is to be used for creating entities when fetching data from the Mongo Server.
	public Question(String title, List<Answer> answers, Drill drill) {
		this.id = UUID.randomUUID();
		this.title = title;
		this.answers = answers;
		this.drill = drill;
	}

	public UUID getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public List<Answer> getAnswers() {
		return Collections.unmodifiableList(answers);
	}

	public Drill getDrill() {
		return drill;
	}

	@Override
	public String toString() {
		return "Question{" +
			"id=" + id +
			", title='" + title + '\'' +
			", answers=" + answers +
			", drill=" + drill +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Question question = (Question) o;

		return id != null ? id.equals(question.id) : question.id == null;

	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
