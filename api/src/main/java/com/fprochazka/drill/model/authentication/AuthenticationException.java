package com.fprochazka.drill.model.authentication;

import com.fprochazka.drill.model.api.ApiException;

public class AuthenticationException extends org.springframework.security.core.AuthenticationException implements ApiException
{

	private String textCode;

	public AuthenticationException(String msg, String textCode, Throwable t)
	{
		super(msg, t);
		this.textCode = textCode;
	}

	public AuthenticationException(String msg, String textCode)
	{
		super(msg);
		this.textCode = textCode;
	}

	public String getTextCode()
	{
		return textCode;
	}

	public String getTextMessage()
	{
		return getMessage();
	}
}
