package data.Exams;

/**
 * Created by Michaela Bamburová on 13.05.2016.
 */
public class QuestionExam {

	private final String questionExamId;
	private final int numCorrect;
	private final int numWrong;

	public QuestionExam(String questionExamId, int numCorrect, int numWrong) {
		this.questionExamId = questionExamId;
		this.numCorrect = numCorrect;
		this.numWrong = numWrong;
	}

	public String getQuestionExamId() {
		return questionExamId;
	}

	public int getNumCorrect() {
		return numCorrect;
	}

	public int getNumWrong() {
		return numWrong;
	}

	@Override
	public String toString() {
		return "QuestionExam{" +
			"questionExamId='" + questionExamId + '\'' +
			", numCorrect=" + numCorrect +
			", numWrong=" + numWrong +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		QuestionExam that = (QuestionExam) o;

		if (numCorrect != that.numCorrect) return false;
		if (numWrong != that.numWrong) return false;
		return questionExamId != null ? questionExamId.equals(that.questionExamId) : that.questionExamId == null;

	}

	@Override
	public int hashCode() {
		int result = questionExamId != null ? questionExamId.hashCode() : 0;
		result = 31 * result + numCorrect;
		result = 31 * result + numWrong;
		return result;
	}
}
