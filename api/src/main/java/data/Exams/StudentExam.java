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

	@Override
	public String toString() {
		return "StudentExam{" +
			"studentExamId='" + studentExamId + '\'' +
			", DrillExams=" + DrillExams +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		StudentExam that = (StudentExam) o;

		if (studentExamId != null ? !studentExamId.equals(that.studentExamId) : that.studentExamId != null)
			return false;
		return DrillExams != null ? DrillExams.equals(that.DrillExams) : that.DrillExams == null;

	}

	@Override
	public int hashCode() {
		int result = studentExamId != null ? studentExamId.hashCode() : 0;
		result = 31 * result + (DrillExams != null ? DrillExams.hashCode() : 0);
		return result;
	}
}
