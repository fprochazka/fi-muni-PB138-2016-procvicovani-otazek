package com.fprochazka.drill.model.authentication.password;

/**
 * Created by viki on 11.06.16.
 */
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
