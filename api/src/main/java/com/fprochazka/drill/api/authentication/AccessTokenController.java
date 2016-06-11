package com.fprochazka.drill.api.authentication;

import com.fprochazka.drill.model.authentication.AccessToken;
import com.fprochazka.drill.model.authentication.AuthenticationException;
import com.fprochazka.drill.model.authentication.AuthenticationFacade;
import com.fprochazka.drill.model.authentication.InvalidPasswordException;
import com.fprochazka.drill.model.student.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccessTokenController
{

	private AccessTokenResponseFactory accessTokenResponseFactory;
	private AuthenticationFacade authenticationFacade;

	@Autowired
	public AccessTokenController(AccessTokenResponseFactory accessTokenResponseFactory, AuthenticationFacade authenticationFacade)
	{
		this.accessTokenResponseFactory = accessTokenResponseFactory;
		this.authenticationFacade = authenticationFacade;
	}

	@RequestMapping(value = "/authentication", method = RequestMethod.POST)
	public @ResponseBody AccessTokenResponse login(
		@RequestBody CreateAccessTokenRequest createAccessTokenRequest
	)
	{
		AccessToken accessToken = null;
		try {
			accessToken = authenticationFacade.createAccessToken(
                createAccessTokenRequest.getName(),
                createAccessTokenRequest.getPassword()
            );
		} catch (StudentNotFoundException | InvalidPasswordException e) {
			throw new AuthenticationException("User not found or invalid password", e);
		}

		return accessTokenResponseFactory.createAccessTokenResponse(accessToken);
	}

}
