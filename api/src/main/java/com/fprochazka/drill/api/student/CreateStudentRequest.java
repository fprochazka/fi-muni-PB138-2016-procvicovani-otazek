package com.fprochazka.drill.api.student;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by viki on 11.06.16.
 */
public class CreateStudentRequest
{
	@NotEmpty
	private int uco;

	@NotEmpty
	private String email;

	@NotEmpty
	@Email
	private String password;

	public CreateStudentRequest(int uco, String email, String password)
	{
		this.uco = uco;
		this.email = email;
		this.password = password;
	}

	public CreateStudentRequest()
	{
	}

	public int getUco()
	{
		return uco;
	}

	public String getEmail()
	{
		return email;
	}

	public String getPassword()
	{
		return password;
	}
}
