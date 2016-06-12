package com.fprochazka.drill.model.authentication.password;

public class UserUcoNotUniqueException extends Exception
{
	public UserUcoNotUniqueException()
	{
	}

	public UserUcoNotUniqueException(String message)
	{
		super(message);
	}

	public UserUcoNotUniqueException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
