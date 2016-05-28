package com.fprochazka.drill.model.student;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Document
@TypeAlias("student")
public class Student {

	@Id
	private final UUID id;
	private final int uco;
	private final String email;
	private final String passwordHash;

	public Student(int uco, String email, String passwordHash) {
		this.id = UUID.randomUUID();
		this.uco = uco;
		this.email = email;
		this.passwordHash = passwordHash;
	}

	public UUID getId() {
		return id;
	}

	public int getUco() {
		return uco;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "Student{" +
			"id='" + id + '\'' +
			", uco=" + uco +
			", email='" + email + '\'' +
			", passwordHash='" + passwordHash + '\'' +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Student student = (Student) o;

		return id != null ? id.equals(student.id) : student.id == null;

	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
