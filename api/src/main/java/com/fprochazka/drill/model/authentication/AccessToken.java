package com.fprochazka.drill.model.authentication;

import com.fprochazka.drill.model.user.User;

public class AccessToken
{
	private final String token;
	private final String scope;
	private final User user;

	public AccessToken(String token, String scope, User user)
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

	public User getUser()
	{
		return user;
	}

}
