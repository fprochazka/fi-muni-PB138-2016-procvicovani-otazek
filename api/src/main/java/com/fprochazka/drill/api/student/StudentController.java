package com.fprochazka.drill.api.student;

import com.fprochazka.drill.model.api.BadRequestException;
import com.fprochazka.drill.model.student.Student;
import com.fprochazka.drill.model.student.StudentEmailNotUniqueException;
import com.fprochazka.drill.model.student.StudentFacade;
import com.fprochazka.drill.model.student.StudentUcoNotUniqueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by viki on 11.06.16.
 */

@RestController
public class StudentController
{

	private StudentFactory studentFactory;
	private StudentFacade studentFacade;

	@Autowired
	public StudentController(StudentFactory studentFactory, StudentFacade studentFacade)
	{
		this.studentFactory = studentFactory;
		this.studentFacade = studentFacade;
	}

	@RequestMapping( value = "/student", method = RequestMethod.POST )
	public void createStudent(@RequestBody CreateStudentRequest request ) throws BadRequestException
	{
		Student student = studentFactory.createStudentFromCreateRequest( request );
		try {
			studentFacade.createStudent( student.getUco(), student.getEmail(), student.getPasswordHash() );
		} catch (StudentUcoNotUniqueException e) {
			throw new BadRequestException( "uco-not-unique", "Student with given UCO already exists." );
		} catch (StudentEmailNotUniqueException e) {
			throw new BadRequestException( "email-not-unique", "Student with given e-mail already exists." );
		}
	}

}
