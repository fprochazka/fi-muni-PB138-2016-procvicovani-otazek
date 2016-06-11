package com.fprochazka.drill.model.api.authentication;

import com.fprochazka.drill.model.student.Student;
import com.fprochazka.drill.model.student.StudentNotFoundException;
import com.fprochazka.drill.model.student.StudentRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthenticationFacade
{

	private StudentRepository studentRepository;
	private String secretKey;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public AuthenticationFacade(
		StudentRepository studentRepository,
		@Value("${jwt.secret}") String secretKey,
		PasswordEncoder passwordEncoder
	)
	{
		this.studentRepository = studentRepository;
		this.secretKey = secretKey;
		this.passwordEncoder = passwordEncoder;
	}

	public AccessToken login(String username, String password) throws StudentNotFoundException, InvalidPasswordException
	{
		Student student = studentRepository.getStudentByUco(Integer.valueOf(username));
		if (student == null) {
			student = studentRepository.getStudentByEmail(username);
		}
		if (student == null) {
			throw new StudentNotFoundException(username);
		}

		student.verifyPassword(passwordEncoder, password);

		String scope = "user";
		String token = Jwts.builder()
			.setSubject(String.valueOf(student.getId()))
			.claim("roles", scope)
			.setIssuedAt(new Date())
			.signWith(SignatureAlgorithm.HS256, secretKey)
			.compact();

		return new AccessToken(token, scope, student);
	}

	public Claims verifyAccessToken(String token)
	{
		return Jwts.parser()
			.setSigningKey(secretKey)
			.parseClaimsJws(token)
			.getBody();
	}

}
