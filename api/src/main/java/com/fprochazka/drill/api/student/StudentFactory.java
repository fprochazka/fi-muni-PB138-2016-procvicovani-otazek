package com.fprochazka.drill.api.student;

import com.fprochazka.drill.model.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by viki on 11.06.16.
 */

@Service
public class StudentFactory
{

	public Student createStudentFromCreateRequest(CreateStudentRequest request )
	{
		return new Student( request.getUco(), request.getEmail(), request.getPasswordHash() );
	}

}
