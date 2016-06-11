package com.fprochazka.drill.model.authentication.password;

import com.fprochazka.drill.model.authentication.AccessToken;
import com.fprochazka.drill.model.user.*;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordAuthenticationFacade
{

	private UserRepository userRepository;
	private String secretKey;
	private PasswordEncoder passwordEncoder;
	private PasswordAuthenticatorService passwordAuthenticatorService;

	@Autowired
	public PasswordAuthenticationFacade(
		UserRepository userRepository,
		@Value("${jwt.secret}") String secretKey,
		PasswordEncoder passwordEncoder,
		PasswordAuthenticatorService passwordAuthenticatorService
	)
	{
		this.userRepository = userRepository;
		this.secretKey = secretKey;
		this.passwordEncoder = passwordEncoder;
		this.passwordAuthenticatorService = passwordAuthenticatorService;
	}

	public AccessToken login(String username, String password) throws UserNotFoundException, InvalidPasswordException
	{
		User user = userRepository.getUserByUco(Integer.valueOf(username));
		if (user == null) {
			user = userRepository.getUserByEmail(username);
		}
		if (user == null) {
			throw new UserNotFoundException(username);
		}
		if (!user.verifyPassword(passwordEncoder, password)) {
			throw new InvalidPasswordException();
		}

		return createAccessToken(user);
	}

	public AccessToken register(int uco, String email, String password) throws UserUcoNotUniqueException, UserEmailNotUniqueException
	{
		User user = new User(uco, email, passwordEncoder.encode(password));

		if (userRepository.getUserByUco(user.getUco()) != null) {
			throw new UserUcoNotUniqueException();
		}
		if (userRepository.getUserByEmail(user.getEmail()) != null) {
			throw new UserEmailNotUniqueException();
		}
		userRepository.save(user);

		return createAccessToken(user);
	}

	private AccessToken createAccessToken(User user)
	{
		String scope = "user";
		String token = passwordAuthenticatorService.createJwtToken(user, scope);

		return new AccessToken(token, scope, user);
	}

}
