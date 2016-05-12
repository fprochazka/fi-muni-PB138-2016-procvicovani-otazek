package data.Exams;

import java.util.List;

/**
 * Created by Michaela Bamburová on 13.05.2016.
 */
public class DrillExam {

	private final String drillExamId;
	private final List<QuestionExam> questionExams;


	public DrillExam(String drillExamId, List<QuestionExam> questionExams) {
		this.drillExamId = drillExamId;
		this.questionExams = questionExams;
	}

	public String getDrillExamId() {
		return drillExamId;
	}

	public List<QuestionExam> getQuestionExams() {
		return questionExams;
	}

	@Override
	public String toString() {
		return "DrillExam{" +
			"drillExamId='" + drillExamId + '\'' +
			", questionExams=" + questionExams +
			'}';
	}
}

