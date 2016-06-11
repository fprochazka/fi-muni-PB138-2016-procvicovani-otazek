package com.fprochazka.drill.model.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentFacade
{

	private StudentRepository studentRepository;

	@Autowired
	public StudentFacade(StudentRepository studentRepository)
	{
		this.studentRepository = studentRepository;
	}

	public Student createStudent(int uco, String email, String passwordHash) throws StudentUcoNotUniqueException
	{
		Student student = new Student(uco, email, passwordHash);

		if (studentRepository.getStudentByUco(student.getUco()) != null) {
			throw new StudentUcoNotUniqueException();
		}
		studentRepository.save(student);

		return student;
	}

}
