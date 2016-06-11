package com.fprochazka.drill.api.authentication.password;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class PasswordRegisterRequest
{
	@NotNull
	private Integer uco;

	@NotEmpty
	private String email;

	@NotEmpty
	private String password;

	public PasswordRegisterRequest(Integer uco, String email, String password)
	{
		this.uco = uco;
		this.email = email;
		this.password = password;
	}

	public PasswordRegisterRequest()
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
