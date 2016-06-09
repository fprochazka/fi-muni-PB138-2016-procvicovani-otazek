package com.fprochazka.drill.model.exam;

import com.fprochazka.drill.model.drill.Drill;
import com.fprochazka.drill.model.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentExamFacade
{

	private StudentExamRepository studentExamRepository;

	@Autowired
	public StudentExamFacade(StudentExamRepository studentExamRepository)
	{
		this.studentExamRepository = studentExamRepository;
	}

	public StudentExam createStudentExam(Drill drill, Student student)
	{
		StudentExam studentExam = new StudentExam(drill, student);
		studentExamRepository.save(studentExam);
		return studentExam;
	}

}
