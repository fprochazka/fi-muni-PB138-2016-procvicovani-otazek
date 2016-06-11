package com.fprochazka.drill.api.authentication.password;

import com.fprochazka.drill.api.user.UserResponse;

public class AccessTokenResponse
{

	private AccessToken accessToken;

	public AccessTokenResponse(String token, String scope, UserResponse user)
	{
		this.accessToken = new AccessToken(token, scope, user);
	}

	public AccessToken getAccessToken()
	{
		return accessToken;
	}

	public class AccessToken
	{
		private String token;
		private String scope;
		private UserResponse user;

		AccessToken(String token, String scope, UserResponse user)
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

		public UserResponse getUser()
		{
			return user;
		}

	}

}
