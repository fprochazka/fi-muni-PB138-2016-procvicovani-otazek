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
	private PasswordEncoder passwordEncoder;
	private PasswordAuthenticatorService passwordAuthenticatorService;

	@Autowired
	public PasswordAuthenticationFacade(
		UserRepository userRepository,
		PasswordEncoder passwordEncoder,
		PasswordAuthenticatorService passwordAuthenticatorService
	)
	{
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.passwordAuthenticatorService = passwordAuthenticatorService;
	}

	public AccessToken login(String username, String password) throws UserNotFoundException, InvalidPasswordException
	{
		User user = null;
		try {
			Integer uco = Integer.valueOf(username);
			user = userRepository.getUserByUco(uco);
		} catch (NumberFormatException ignored) {
		}
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
		if (userRepository.getUserByUco(uco) != null) {
			throw new UserUcoNotUniqueException();
		}
		if (userRepository.getUserByEmail(email) != null) {
			throw new UserEmailNotUniqueException();
		}

		User user = new User(uco, email, passwordEncoder.encode(password));
		userRepository.save(user);

		return createAccessToken(user);
	}

	private AccessToken createAccessToken(User user)
	{
		return new AccessToken(
			passwordAuthenticatorService.createJwtToken(user, AccessToken.ROLE_USER),
			user
		);
	}

}
