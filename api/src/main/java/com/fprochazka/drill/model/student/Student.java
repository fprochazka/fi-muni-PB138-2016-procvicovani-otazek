package com.fprochazka.drill.model.student;

import com.fprochazka.drill.model.Identified;
import com.fprochazka.drill.model.authentication.InvalidPasswordException;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.password.PasswordEncoder;

@Document(collection = "students")
@TypeAlias("student")
public class Student extends Identified
{

	private final int uco;
	private final String email;
	private final String passwordHash;

	public Student(int uco, String email, String passwordHash)
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

	public void verifyPassword(PasswordEncoder passwordEncoder, String password) throws InvalidPasswordException
	{
		if (!passwordEncoder.matches(password, passwordHash)) {
			throw new InvalidPasswordException();
		}
	}

	@Override
	public String toString()
	{
		return "Student{" +
			"id='" + getId() + '\'' +
			", uco=" + uco +
			", email='" + email + '\'' +
			", passwordHash='" + passwordHash + '\'' +
			'}';
	}
}
