package com.fprochazka.drill.api.student;

import com.fprochazka.drill.model.api.BadRequestException;
import com.fprochazka.drill.model.student.Student;
import com.fprochazka.drill.model.student.StudentEmailNotUniqueException;
import com.fprochazka.drill.model.student.StudentFacade;
import com.fprochazka.drill.model.student.StudentUcoNotUniqueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by viki on 11.06.16.
 */

@RestController
public class StudentController
{
	private PasswordEncoder passwordEncoder;
	private StudentFactory studentFactory;
	private StudentFacade studentFacade;

	@Autowired
	public StudentController(PasswordEncoder passwordEncoder, StudentFactory studentFactory, StudentFacade studentFacade)
	{
		this.passwordEncoder = passwordEncoder;
		this.studentFactory = studentFactory;
		this.studentFacade = studentFacade;
	}

	@RequestMapping( value = "/student", method = RequestMethod.POST )
	public @ResponseBody StudentResponse createStudent(@Valid @RequestBody CreateStudentRequest request ) throws BadRequestException
	{
		try {
			String passwordHash = passwordEncoder.encode( request.getPassword() );
			Student student = studentFacade.createStudent( request.getUco(), request.getEmail(), passwordHash );
			return studentFactory.createStudentResponse( student );
		} catch (StudentUcoNotUniqueException e) {
			throw new BadRequestException( "uco-not-unique", "Student with given UCO already exists." );
		} catch (StudentEmailNotUniqueException e) {
			throw new BadRequestException( "email-not-unique", "Student with given e-mail already exists." );
		}
	}

}
