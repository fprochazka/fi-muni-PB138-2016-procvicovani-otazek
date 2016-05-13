package data.Exams;

import java.util.List;

/**
 * Created by Michaela Bamburová on 13.05.2016.
 */
public class Exam {

	private final String examId;
	private final String drillId;
	private final List<QuestionExam> questionExams;


	public Exam(String examId, String drillId, List<QuestionExam> questionExams) {
		this.examId = examId;
		this.drillId = drillId;
		this.questionExams = questionExams;
	}

	public String getDrillId() {
		return drillId;
	}

	public List<QuestionExam> getQuestionExams() {
		return questionExams;
	}

	public String getExamId() {
		return examId;
	}

	@Override
	public String toString() {
		return "Exam{" +
			"examId='" + examId + '\'' +
			", drillId='" + drillId + '\'' +
			", questionExams=" + questionExams +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Exam exam = (Exam) o;

		return examId != null ? examId.equals(exam.examId) : exam.examId == null;

	}

	@Override
	public int hashCode() {
		return examId != null ? examId.hashCode() : 0;
	}
}
