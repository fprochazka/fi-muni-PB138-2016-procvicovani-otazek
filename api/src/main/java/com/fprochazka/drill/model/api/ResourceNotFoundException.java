package com.fprochazka.drill.model.api;

public class ResourceNotFoundException extends Exception implements ApiException
{

	private String textCode;
	private String textMessage;

	public ResourceNotFoundException(String textCode, String textMessage)
	{
		super(textMessage);
		this.textCode = textCode;
		this.textMessage = textMessage;
	}

	public ResourceNotFoundException(Throwable cause, String textCode, String textMessage)
	{
		super(textMessage, cause);
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
