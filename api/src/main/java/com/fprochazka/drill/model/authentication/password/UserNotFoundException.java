package com.fprochazka.drill.model.authentication.password;

public class UserNotFoundException extends Exception
{

	private String username;

	public UserNotFoundException(String username)
	{
		this.username = username;
	}

	public UserNotFoundException()
	{
	}

	public String getUsername()
	{
		return username;
	}
}
