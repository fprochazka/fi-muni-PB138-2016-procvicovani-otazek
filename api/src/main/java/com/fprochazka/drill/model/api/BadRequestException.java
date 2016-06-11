package com.fprochazka.drill.model.api;

public class BadRequestException extends Exception implements ApiException
{

	private String textCode;
	private String textMessage;

	public BadRequestException(String textCode, String textMessage)
	{
		this.textCode = textCode;
		this.textMessage = textMessage;
	}

	public BadRequestException(Throwable cause, String textCode, String textMessage)
	{
		super(cause);
		this.textCode = textCode;
		this.textMessage = textMessage;
	}

	public String getTextCode()
	{
		return textCode;
	}

	public String getTextMessage()
	{
		return textMessage;
	}
}
