package com.fprochazka.drill.api.student;

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
	private String passwordHash;

	public CreateStudentRequest(int uco, String email, String passwordHash)
	{
		this.uco = uco;
		this.email = email;
		this.passwordHash = passwordHash;
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

	public String getPasswordHash()
	{
		return passwordHash;
	}
}
