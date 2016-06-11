package com.fprochazka.drill.model.user;

import com.fprochazka.drill.model.Identified;
import com.fprochazka.drill.model.authentication.password.InvalidPasswordException;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.password.PasswordEncoder;

@Document(collection = "users")
@TypeAlias("user")
public class User extends Identified
{

	private final int uco;
	private final String email;
	private final String passwordHash;

	public User(int uco, String email, String passwordHash)
	{
		super();
		this.uco = uco;
		this.email = email;
		this.passwordHash = passwordHash;
	}

	public int getUco()
	{
		return uco;
	}

	public String getEmail()
	{
		return email;
	}

	public boolean verifyPassword(PasswordEncoder passwordEncoder, String password)
	{
		return passwordEncoder.matches(password, passwordHash);
	}

	@Override
	public String toString()
	{
		return "User{" +
			"id='" + getId() + '\'' +
			", uco=" + uco +
			", email='" + email + '\'' +
			'}';
	}
}
