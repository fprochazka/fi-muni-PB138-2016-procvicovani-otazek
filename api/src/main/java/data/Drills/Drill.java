package data.Drills;

import java.util.List;
import java.util.UUID;

/**
 * Created by Michaela Bamburová on 13.05.2016.
 */
public class Drill {

	private final UUID id;
	private final String subject;
	private final String description;
	private final List<Question> questions;

	public Drill(String subject, String description, List<Question> questions) {
		this.id = UUID.randomUUID();
		this.subject = subject;
		this.description = description;
		this.questions = questions;
	}

	public List<Question> getQuestions() {
		return questions;
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
			", questions=" + questions +
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
