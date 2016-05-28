package com.fprochazka.drill.model.drill;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Document
@TypeAlias("drill")
public class Drill {

	@Id
	private final UUID id;
	private final String subject;
	private final String description;

	public Drill(String subject, String description) {
		this.id = UUID.randomUUID();
		this.subject = subject;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public String getSubject() {
		return subject;
	}

	public UUID getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Drill{" +
			"id=" + id +
			", subject='" + subject + '\'' +
			", description='" + description + '\'' +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Drill drill = (Drill) o;

		return id != null ? id.equals(drill.id) : drill.id == null;

	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
