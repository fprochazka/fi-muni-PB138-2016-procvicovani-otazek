package data.Exams;

/**
 * Created by Michaela Bamburová on 13.05.2016.
 */
public class QuestionExam {

	private final String questionExamId;
	private final String questionId;
	private final int numCorrect;
	private final int numWrong;

	public QuestionExam(String questionExamId, String questionId, int numCorrect, int numWrong) {
		this.questionExamId = questionExamId;
		this.questionId = questionId;
		this.numCorrect = numCorrect;
		this.numWrong = numWrong;
	}

	public String getQuestionExamId() {
		return questionExamId;
	}

	public int getNumWrong() {
		return numWrong;
	}

	public int getNumCorrect() {
		return numCorrect;
	}

	public String getQuestionId() {
		return questionId;
	}

	@Override
	public String toString() {
		return "QuestionExam{" +
			"questionExamId='" + questionExamId + '\'' +
			", questionId='" + questionId + '\'' +
			", numCorrect=" + numCorrect +
			", numWrong=" + numWrong +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		QuestionExam that = (QuestionExam) o;

		return questionExamId != null ? questionExamId.equals(that.questionExamId) : that.questionExamId == null;

	}

	@Override
	public int hashCode() {
		return questionExamId != null ? questionExamId.hashCode() : 0;
	}
}
