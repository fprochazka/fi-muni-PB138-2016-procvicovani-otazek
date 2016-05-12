package data.Exams;

import java.util.List;

/**
 * Created by Michaela Bamburová on 13.05.2016.
 */
public class StudentExam {

	private final String studentExamId;
	private final List<DrillExam> DrillExams;

	public StudentExam(String studentExamId, List<DrillExam> drillExams) {
		this.studentExamId = studentExamId;
		DrillExams = drillExams;
	}

	public String getStudentExamId() {
		return studentExamId;
	}

	public List<DrillExam> getDrillExams() {
		return DrillExams;
	}

}
