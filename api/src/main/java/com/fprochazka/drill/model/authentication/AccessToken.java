package com.fprochazka.drill.model.authentication;

import com.fprochazka.drill.model.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class AccessToken implements Authentication
{
	public static final String ROLE_USER = "ROLE_USER";

	private final String token;
	private final User user;
	private boolean authenticated;
	private Object details;

	public AccessToken(String token)
	{
		this.token = token;
		this.user = null;
		this.authenticated = false;
	}

	public AccessToken(String token, User user)
	{
		this.token = token;
		this.user = user;
		this.authenticated = true;
	}

	public String getToken()
	{
		return token;
	}

	public String getScope()
	{
		return ROLE_USER;
	}

	public User getUser()
	{
		return user;
	}

	public List<GrantedAuthority> getAuthorities()
	{
		if (!isAuthenticated()) {
			return new ArrayList<>();
		}

		return new ArrayList<GrantedAuthority>() {{
			add(new SimpleGrantedAuthority(getScope()));
		}};
	}

	@Override
	public Object getCredentials()
	{
		return token;
	}

	@Override
	public Object getDetails()
	{
		return details;
	}

	public void setDetails(Object details)
	{
		this.details = details;
	}

	@Override
	public Object getPrincipal()
	{
		return user;
	}

	@Override
	public boolean isAuthenticated()
	{
		return false;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException
	{
		if (isAuthenticated) {
			throw new IllegalArgumentException("Cannot set this token to trusted");
		} else {
			authenticated = false;
		}
	}

	@Override
	public String getName()
	{
		return user != null ? String.valueOf(user.getId()) : "";
	}
}
