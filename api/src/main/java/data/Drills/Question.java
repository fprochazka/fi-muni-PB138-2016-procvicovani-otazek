package data.Drills;

import java.util.List;
import java.util.UUID;

/**
 * Created by Michaela Bamburová on 13.05.2016.
 */
public class Question {

	private final UUID id;
	private final String title;
	private final List<Answer> answers;

	public Question(String title, List<Answer> answers) {
		this.id = UUID.randomUUID();
		this.title = title;
		this.answers = answers;
	}

	public UUID getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	@Override
	public String toString() {
		return "Question{" +
			"id='" + id + '\'' +
			", title='" + title + '\'' +
			", answers=" + answers +
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
