package data;

import data.Exams.Exam;

import java.util.List;

/**
 * Created by Michaela Bamburová on 13.05.2016.
 */
public class Student {

	private final String studentId;
	private final int uco;
	private final String email;
	private final String passwordHash;
	private final List<Exam> exams;

	public Student(String studentId, int uco, String email, String passwordHash, List<Exam> exams) {
		this.studentId = studentId;
		this.uco = uco;
		this.email = email;
		this.passwordHash = passwordHash;
		this.exams = exams;
	}

	public String getStudentId() {
		return studentId;
	}

	public int getUco() {
		return uco;
	}

	public String getEmail() {
		return email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public List<Exam> getExams() {
		return exams;
	}

	@Override
	public String toString() {
		return "Student{" +
			"studentId='" + studentId + '\'' +
			", uco=" + uco +
			", email='" + email + '\'' +
			", passwordHash='" + passwordHash + '\'' +
			", exams=" + exams +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Student student = (Student) o;

		return studentId != null ? studentId.equals(student.studentId) : student.studentId == null;

	}

	@Override
	public int hashCode() {
		return studentId != null ? studentId.hashCode() : 0;
	}
}
