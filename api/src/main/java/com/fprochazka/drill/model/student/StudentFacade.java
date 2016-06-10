package com.fprochazka.drill.model.student;

import com.fprochazka.drill.model.exceptions.NotUniqueException;
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

	public Student createStudent(int uco, String email, String passwordHash) throws NotUniqueException
	{
		Student student = new Student(uco, email, passwordHash);

		if (studentRepository.getStudentByUco(student.getUco()) != null) {
			throw new NotUniqueException();
		}
		studentRepository.save(student);

		return student;
	}

}
