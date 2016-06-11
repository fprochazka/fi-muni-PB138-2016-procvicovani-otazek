package com.fprochazka.drill.model.authentication.password;

import com.fprochazka.drill.model.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PasswordAuthenticatorService
{

	private String secretKey;

	@Autowired
	public PasswordAuthenticatorService(
		@Value("${jwt.secret}") String secretKey
	)
	{
		this.secretKey = secretKey;
	}

	public String createJwtToken(User user, String scope)
	{
		return Jwts.builder()
			.setSubject(String.valueOf(user.getId()))
			.claim("roles", scope)
			.setIssuedAt(new Date())
			.signWith(SignatureAlgorithm.HS256, secretKey)
			.compact();
	}

	public Claims verifyJwtToken(String token)
	{
		return Jwts.parser()
			.setSigningKey(secretKey)
			.parseClaimsJws(token)
			.getBody();
	}

}
