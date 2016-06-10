package com.fprochazka.drill.fixtures;

import com.fprochazka.drill.model.student.Student;
import com.fprochazka.drill.model.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentTestFixtures
{

	public static Student student123456;

	@Autowired
	private StudentRepository studentRepository;

	public void install()
	{
		student123456 = new Student(123456, "mail@mail.com", "hash");
		studentRepository.save(student123456);
	}
}
