package com.fprochazka.drill.api.authentication.password;

import com.fprochazka.drill.model.api.BadRequestException;
import com.fprochazka.drill.model.authentication.AccessToken;
import com.fprochazka.drill.model.authentication.AuthenticationException;
import com.fprochazka.drill.model.authentication.password.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PasswordAuthenticationController
{

	public final static String CODE_LOGIN_INVALID_PASSWORD = "invalid-password";
	public final static String CODE_USER_NO_REGISTRATION = "user-no-registration";

	private PasswordAuthenticationResponseFactory accessTokenResponseFactory;
	private PasswordAuthenticationFacade authenticationFacade;

	@Autowired
	public PasswordAuthenticationController(PasswordAuthenticationResponseFactory accessTokenResponseFactory, PasswordAuthenticationFacade authenticationFacade)
	{
		this.accessTokenResponseFactory = accessTokenResponseFactory;
		this.authenticationFacade = authenticationFacade;
	}

	@RequestMapping(value = "/authentication/password/login", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> login(
		@RequestBody PasswordLoginRequest passwordLoginRequest
	)
	{
		try {
			AccessToken accessToken = authenticationFacade.login(
				passwordLoginRequest.getUsername(),
				passwordLoginRequest.getPassword()
			);

			return new HashMap<String, Object>(){{
				put("accessToken", accessTokenResponseFactory.createAccessTokenResponse(accessToken));
			}};

		} catch (UserNotFoundException e) {
			throw new AuthenticationException("There is no registration for username", CODE_USER_NO_REGISTRATION, e);
		} catch (InvalidPasswordException e) {
			throw new AuthenticationException("Password does not match", CODE_LOGIN_INVALID_PASSWORD, e);
		}
	}

	@RequestMapping(value = "/authentication/password/register", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> register(@Valid @RequestBody PasswordRegisterRequest request) throws BadRequestException
	{
		try {
			AccessToken accessToken = authenticationFacade.register(request.getUco(), request.getEmail(), request.getPassword());

			return new HashMap<String, Object>(){{
				put("accessToken", accessTokenResponseFactory.createAccessTokenResponse(accessToken));
			}};

		} catch (UserUcoNotUniqueException e) {
			throw new BadRequestException("uco-not-unique", "User with given UCO already exists.");
		} catch (UserEmailNotUniqueException e) {
			throw new BadRequestException("email-not-unique", "User with given e-mail already exists.");
		}
	}

}
