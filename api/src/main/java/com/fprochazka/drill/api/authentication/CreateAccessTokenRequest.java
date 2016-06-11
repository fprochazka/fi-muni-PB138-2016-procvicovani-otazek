package com.fprochazka.drill.api.authentication;

public class CreateAccessTokenRequest
{
	private String name;
	private String password;

	public CreateAccessTokenRequest()
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
