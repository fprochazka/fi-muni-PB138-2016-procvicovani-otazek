package com.fprochazka.drill.api.authentication.password;

public class PasswordLoginRequest
{
	private String username;
	private String password;

	public PasswordLoginRequest()
	{
	}

	public String getUsername()
	{
		return username;
	}

	public String getPassword()
	{
		return password;
	}
}
