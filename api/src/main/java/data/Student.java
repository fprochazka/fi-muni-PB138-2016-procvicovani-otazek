package data;

/**
 * Created by Michaela Bamburová on 13.05.2016.
 */
public class Student {

	private final String studentId;
	private final int uco;
	private final String email;
	private final String passwordHash;
	private final String studentExamId;


	public Student(String studentId, int uco, String email, String passwordHash, String studentExamId) {
		this.studentId = studentId;
		this.uco = uco;
		this.email = email;
		this.passwordHash = passwordHash;
		this.studentExamId = studentExamId;
	}

	public String getStudentId() {
		return studentId;
	}

	public String getEmail() {
		return email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public int getUco() {
		return uco;
	}

	public String getStudentExamId() {
		return studentExamId;
	}

	@Override
	public String toString() {
		return "Student{" +
			"studentId='" + studentId + '\'' +
			", uco=" + uco +
			", email='" + email + '\'' +
			", passwordHash='" + passwordHash + '\'' +
			", studentExamId='" + studentExamId + '\'' +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Student student = (Student) o;

		if (uco != student.uco) return false;
		if (studentId != null ? !studentId.equals(student.studentId) : student.studentId != null) return false;
		if (email != null ? !email.equals(student.email) : student.email != null) return false;
		if (passwordHash != null ? !passwordHash.equals(student.passwordHash) : student.passwordHash != null)
			return false;
		return studentExamId != null ? studentExamId.equals(student.studentExamId) : student.studentExamId == null;

	}

	@Override
	public int hashCode() {
		int result = studentId != null ? studentId.hashCode() : 0;
		result = 31 * result + uco;
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (passwordHash != null ? passwordHash.hashCode() : 0);
		result = 31 * result + (studentExamId != null ? studentExamId.hashCode() : 0);
		return result;
	}
}
