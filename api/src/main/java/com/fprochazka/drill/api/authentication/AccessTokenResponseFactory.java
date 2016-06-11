package com.fprochazka.drill.api.authentication;

import com.fprochazka.drill.model.api.authentication.AccessToken;
import com.fprochazka.drill.model.student.Student;
import org.springframework.stereotype.Service;

@Service
public class AccessTokenResponseFactory
{

	public AccessTokenResponse createAccessTokenResponse(AccessToken accessToken)
	{
		return new AccessTokenResponse(
			accessToken.getToken(),
			accessToken.getScope(),
			createUserResponse(accessToken.getUser())
		);
	}

	private UserResponse createUserResponse(Student user)
	{
		return new UserResponse(user.getId(), user.getUco(), user.getEmail());
	}

}
