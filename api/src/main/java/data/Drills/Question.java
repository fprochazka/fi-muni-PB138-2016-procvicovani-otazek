package data.Drills;

import java.util.List;
import java.util.UUID;

/**
 * Created by Michaela Bamburová on 13.05.2016.
 */
public class Question {

	private final UUID questionId;
	private final String title;
	private final List<Answer> answers;

	public Question(String title, List<Answer> answers) {
		this.questionId = UUID.randomUUID();
		this.title = title;
		this.answers = answers;
	}

	public UUID getQuestionId() {
		return questionId;
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
			"questionId='" + questionId + '\'' +
			", title='" + title + '\'' +
			", answers=" + answers +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Question question = (Question) o;

		return questionId != null ? questionId.equals(question.questionId) : question.questionId == null;

	}

	@Override
	public int hashCode() {
		return questionId != null ? questionId.hashCode() : 0;
	}
}
