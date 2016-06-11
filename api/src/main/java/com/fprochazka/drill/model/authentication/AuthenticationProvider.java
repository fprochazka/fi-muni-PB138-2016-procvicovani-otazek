package com.fprochazka.drill.model.authentication;

import com.fprochazka.drill.model.authentication.password.PasswordAuthenticatorService;
import com.fprochazka.drill.model.user.User;
import com.fprochazka.drill.model.user.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

public class AuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider
{

	private UserRepository userRepository;
	private PasswordAuthenticatorService passwordAuthenticatorService;

	@Autowired
	public AuthenticationProvider(UserRepository userRepository, PasswordAuthenticatorService passwordAuthenticatorService)
	{
		this.userRepository = userRepository;
		this.passwordAuthenticatorService = passwordAuthenticatorService;
	}

	@Override
	public org.springframework.security.core.Authentication authenticate(org.springframework.security.core.Authentication authentication) throws org.springframework.security.core.AuthenticationException
	{
		AccessToken apiToken = (AccessToken) authentication;

		// api key (aka username)
		String token = apiToken.getToken();
		if (token == null) {
			throw new AuthenticationException("Invalid token", "invalid-authorization-token");
		}

		Claims claims;
		try {
			claims = passwordAuthenticatorService.verifyJwtToken(token);

		} catch (final JwtException e) {
			throw new AuthenticationException("Invalid token", "invalid-authentication-token", e);
		}

		UUID userId = UUID.fromString(claims.getSubject());
		User user = userRepository.getUserById(userId);
		if (user == null) {
			throw new AuthenticationException("Invalid token", "invalid-authorization-token");
		}

		return new AccessToken(token, user) {{
			setDetails(apiToken.getDetails());
		}};
	}

	public boolean supports(Class<?> authentication) {
		return AccessToken.class.equals(authentication);
	}

}
