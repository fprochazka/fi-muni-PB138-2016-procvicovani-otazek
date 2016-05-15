package com.fprochazka.drill.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Reference;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by Michaela Bamburová on 13.05.2016.
 */
@Document
public class Drill {

	@Id
	@Field(value = "_id")
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
