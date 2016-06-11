package com.fprochazka.drill.api.authentication.password;

public class PasswordLoginRequest
{
	private String name;
	private String password;

	public PasswordLoginRequest()
	{
	}

	public String getName()
	{
		return name;
	}

	public String getPassword()
	{
		return password;
	}
}
