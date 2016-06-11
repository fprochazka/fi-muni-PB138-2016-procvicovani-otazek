package com.fprochazka.drill.model.api;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.fprochazka.drill.model.api.authentication.AuthenticationFacade;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;

public class JwtFilter extends GenericFilterBean
{

	public static final String AUTHORIZATION_TOKEN_HEADER = "X-Authorization-Token";

	private AuthenticationFacade authenticationFacade;

	public JwtFilter(AuthenticationFacade authenticationFacade)
	{
		this.authenticationFacade = authenticationFacade;
	}

	@Override
	public void doFilter(
		final ServletRequest req,
		final ServletResponse res,
		final FilterChain chain
	) throws IOException, ServletException
	{
		final HttpServletRequest request = (HttpServletRequest) req;

		final String authHeader = request.getHeader(AUTHORIZATION_TOKEN_HEADER);
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			final String token = authHeader.substring(7); // The part after "Bearer "

			try {
				final Claims claims = authenticationFacade.verifyAccessToken(token);
				request.setAttribute("claims", claims);

			} catch (final SignatureException e) {
				throw new ServletException("Invalid token.", e);
			}
		}

		chain.doFilter(req, res);
	}

}
