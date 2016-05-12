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
}
