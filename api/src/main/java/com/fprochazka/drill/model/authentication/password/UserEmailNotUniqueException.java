package com.fprochazka.drill.model.authentication.password;

public class UserEmailNotUniqueException extends Exception
{
	public UserEmailNotUniqueException()
	{
	}

	public UserEmailNotUniqueException(String message)
	{
		super(message);
	}

	public UserEmailNotUniqueException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
