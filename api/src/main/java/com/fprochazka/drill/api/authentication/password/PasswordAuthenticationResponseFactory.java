package com.fprochazka.drill.api.authentication.password;

import com.fprochazka.drill.api.user.UserResponse;
import com.fprochazka.drill.api.user.UserResponseFactory;
import com.fprochazka.drill.model.authentication.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordAuthenticationResponseFactory
{

	private UserResponseFactory userResponseFactory;

	@Autowired
	public PasswordAuthenticationResponseFactory(UserResponseFactory userResponseFactory)
	{
		this.userResponseFactory = userResponseFactory;
	}

	public AccessTokenResponse createAccessTokenResponse(AccessToken accessToken)
	{
		return new AccessTokenResponse(
			accessToken.getToken(),
			accessToken.getScope(),
			userResponseFactory.createUserResponse(accessToken.getUser())
		);
	}

}
