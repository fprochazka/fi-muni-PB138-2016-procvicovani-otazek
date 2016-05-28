package com.fprochazka.drill.model.exam;

import com.fprochazka.drill.model.drill.Drill;
import com.fprochazka.drill.model.student.Student;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Document
@TypeAlias("student_exam")
public class StudentExam {

	@Id
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
