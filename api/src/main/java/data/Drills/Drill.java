package data.Drills;

import java.util.List;

/**
 * Created by Michaela Bamburová on 13.05.2016.
 */
public class Drill {

	private final String drillId;
	private final String subject;
	private final String description;
	private final List<Question> questions;


	public Drill(String drillId, String subject, String description, List<Question> questions) {
		this.drillId = drillId;
		this.subject = subject;
		this.description = description;
		this.questions = questions;
	}

	public String getDrillId() {
		return drillId;
	}

	public String getSubject() {
		return subject;
	}

	public String getDescription() {
		return description;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	@Override
	public String toString() {
		return "Drill{" +
			"drillId='" + drillId + '\'' +
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

		return drillId != null ? drillId.equals(drill.drillId) : drill.drillId == null;

	}

	@Override
	public int hashCode() {
		return drillId != null ? drillId.hashCode() : 0;
	}
}
