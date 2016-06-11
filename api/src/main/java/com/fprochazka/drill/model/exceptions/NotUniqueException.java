package com.fprochazka.drill.model.exceptions;

/**
 * Created by Michaela Bamburová on 11.06.2016.
 */
public class NotUniqueException extends Exception
{

	private Class objectName;
	private String field;
	private String rejectedValue;

	public NotUniqueException()
	{
	}

	public NotUniqueException( Class objectName, String field, String rejectedValue )
	{
		this.objectName = objectName;
		this.field = field;
		this.rejectedValue = rejectedValue;
	}

	public NotUniqueException(String message)
	{
		super(message);
	}

	public NotUniqueException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public Class getObjectName()
	{
		return objectName;
	}

	public String getField()
	{
		return field;
	}

	public String getRejectedValue()
	{
		return rejectedValue;
	}
}
