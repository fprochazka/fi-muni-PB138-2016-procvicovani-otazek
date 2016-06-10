package com.fprochazka.drill.model.authentication;

import com.fprochazka.drill.model.student.Student;

public class AccessToken
{
	private final String token;
	private final String scope;
	private final Student user;

	public AccessToken(String token, String scope, Student user)
	{
		this.token = token;
		this.scope = scope;
		this.user = user;
	}

	public String getToken()
	{
		return token;
	}

	public String getScope()
	{
		return scope;
	}

	public Student getUser()
	{
		return user;
	}

}
