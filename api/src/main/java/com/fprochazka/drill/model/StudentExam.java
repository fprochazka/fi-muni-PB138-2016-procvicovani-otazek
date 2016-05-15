package com.fprochazka.drill.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by Michaela Bamburová on 13.05.2016.
 */
@Document
public class StudentExam {

	@Id
	@Field(value = "_id")
	private final UUID id;
	private final Drill drill;
	private final Student student;

	public StudentExam(Drill drill, Student student) {
		this.id = UUID.randomUUID();
		this.drill = drill;
		this.student = student;
	}

	public UUID getId() {
		return id;
	}

	public Drill getDrill() {
		return drill;
	}

	public Student getStudent() {
		return student;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		StudentExam exam = (StudentExam) o;

		return id != null ? id.equals(exam.id) : exam.id == null;

	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
