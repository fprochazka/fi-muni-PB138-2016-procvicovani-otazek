package com.fprochazka.drill.api.authentication.password;

import com.fprochazka.drill.api.authentication.AccessTokenResponse;
import com.fprochazka.drill.api.authentication.AccessTokenResponseFactory;
import com.fprochazka.drill.model.api.authentication.AccessToken;
import com.fprochazka.drill.model.api.authentication.AuthenticationException;
import com.fprochazka.drill.model.api.authentication.AuthenticationFacade;
import com.fprochazka.drill.model.api.authentication.InvalidPasswordException;
import com.fprochazka.drill.model.student.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PasswordController
{

	public final static String CODE_LOGIN_INVALID_PASSWORD = "invalid-password";
	public final static String CODE_USER_NO_REGISTRATION = "user-no-registration";

	private AccessTokenResponseFactory accessTokenResponseFactory;
	private AuthenticationFacade authenticationFacade;

	@Autowired
	public PasswordController(AccessTokenResponseFactory accessTokenResponseFactory, AuthenticationFacade authenticationFacade)
	{
		this.accessTokenResponseFactory = accessTokenResponseFactory;
		this.authenticationFacade = authenticationFacade;
	}

	@RequestMapping(value = "/authentication/password/login", method = RequestMethod.POST)
	public @ResponseBody AccessTokenResponse login(
		@RequestBody PasswordLoginRequest passwordLoginRequest
	)
	{
		try {
			AccessToken accessToken = authenticationFacade.login(
				passwordLoginRequest.getName(),
				passwordLoginRequest.getPassword()
			);

			return accessTokenResponseFactory.createAccessTokenResponse(accessToken);

		} catch (StudentNotFoundException e) {
			throw new AuthenticationException("There is no registration for username", CODE_USER_NO_REGISTRATION, e);
		} catch (InvalidPasswordException e) {
			throw new AuthenticationException("Password does not match", CODE_LOGIN_INVALID_PASSWORD, e);
		}
	}

}
