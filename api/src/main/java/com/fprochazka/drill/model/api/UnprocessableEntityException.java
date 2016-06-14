package com.fprochazka.drill.model.api;

public class UnprocessableEntityException extends Exception implements ApiException
{

	private String textCode;
	private String textMessage;

	public UnprocessableEntityException(String textCode, String textMessage)
	{
		super(textMessage);
		this.textCode = textCode;
		this.textMessage = textMessage;
	}

	public UnprocessableEntityException(Throwable cause, String textCode, String textMessage)
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
