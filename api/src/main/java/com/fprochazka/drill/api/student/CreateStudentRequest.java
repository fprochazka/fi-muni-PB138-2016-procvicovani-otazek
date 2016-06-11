package com.fprochazka.drill.api.student;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by viki on 11.06.16.
 */
public class CreateStudentRequest
{
	@NotNull
	private Integer uco;

	@NotEmpty
	private String email;

	@NotEmpty
	private String password;

	public CreateStudentRequest(Integer uco, String email, String password)
	{
		this.uco = uco;
		this.email = email;
		this.password = password;
	}

	public CreateStudentRequest()
	{
	}

	public Integer getUco()
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
