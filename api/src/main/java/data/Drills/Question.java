package data.Drills;

import java.util.List;

/**
 * Created by Michaela Bamburová on 13.05.2016.
 */
public class Question {

	private final String questionId;
	private final String title;
	private final List<Answer> answers;

	public Question(String questionId, String title, List<Answer> answers) {
		this.questionId = questionId;
		this.title = title;
		this.answers = answers;
	}

	public String getQuestionId() {
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

		if (questionId != null ? !questionId.equals(question.questionId) : question.questionId != null) return false;
		if (title != null ? !title.equals(question.title) : question.title != null) return false;
		return answers != null ? answers.equals(question.answers) : question.answers == null;

	}

	@Override
	public int hashCode() {
		int result = questionId != null ? questionId.hashCode() : 0;
		result = 31 * result + (title != null ? title.hashCode() : 0);
		result = 31 * result + (answers != null ? answers.hashCode() : 0);
		return result;
	}
}
