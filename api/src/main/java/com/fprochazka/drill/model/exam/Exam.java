package com.fprochazka.drill.model.exam;

import com.fprochazka.drill.model.Identified;
import com.fprochazka.drill.model.drill.Drill;
import com.fprochazka.drill.model.student.Student;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "student_exams")
@TypeAlias("student_exam")
public class Exam extends Identified
{

	private final Drill drill;
	private final Student student;

	public Exam(Drill drill, Student student)
	{
		super();
		this.drill = drill;
		this.student = student;
	}

	public Drill getDrill()
	{
		return drill;
	}

	public Student getStudent()
	{
		return student;
	}

}
