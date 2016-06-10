package com.fprochazka.drill.model.student;

public class StudentNotFoundException extends Exception
{

	private String username;

	public StudentNotFoundException(String username)
	{
		this.username = username;
	}

	public String getUsername()
	{
		return username;
	}
}
