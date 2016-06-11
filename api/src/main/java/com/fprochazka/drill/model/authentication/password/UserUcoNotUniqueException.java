package com.fprochazka.drill.model.authentication.password;

/**
 * Created by viki on 11.06.16.
 */
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
