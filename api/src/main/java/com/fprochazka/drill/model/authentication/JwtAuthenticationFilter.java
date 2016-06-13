package com.fprochazka.drill.model.authentication;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

@Service
public class JwtAuthenticationFilter extends OncePerRequestFilter
{

	public static final String AUTHORIZATION_TOKEN_HEADER = "X-Authorization-Token";

	private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new WebAuthenticationDetailsSource();
	private AuthenticationManager authenticationManager;
	private AuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager)
	{
		this.authenticationManager = authenticationManager;
	}

	@Autowired
	public void setAuthenticationEntryPoint(AuthenticationEntryPoint authenticationEntryPoint)
	{
		this.authenticationEntryPoint = authenticationEntryPoint;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest httpRequest, HttpServletResponse httpResponse, FilterChain chain) throws ServletException, IOException
	{
		final String authHeader = httpRequest.getHeader(AUTHORIZATION_TOKEN_HEADER);
		if (authHeader == null) {
			chain.doFilter(httpRequest, httpResponse);
			return;
		}

		try {
			if (!authHeader.startsWith("Bearer ")) {
				throw new AuthenticationException("Broken authorization header provided", "invalid-authorization-token-header");
			}
			final String token = authHeader.substring(7); // The part after "Bearer "

			// Create an authentication token
			AccessToken authenticationRequest = new AccessToken(token);
			authenticationRequest.setDetails(this.authenticationDetailsSource.buildDetails(httpRequest));

			// Request the authentication manager to authenticate the token (throws exception)
			Authentication successfulAuthentication = authenticationManager.authenticate(authenticationRequest);

			// Pass the successful token to the SecurityHolder where it can be retrieved by this thread at any stage.
			SecurityContextHolder.getContext().setAuthentication(successfulAuthentication);

			if (successfulAuthentication instanceof AccessToken) {
				httpRequest.setAttribute("accessToken", successfulAuthentication);
			}

			// Continue with the Filters
			chain.doFilter(httpRequest, httpResponse);

		} catch (org.springframework.security.core.AuthenticationException authenticationException) {
			SecurityContextHolder.clearContext();
			authenticationEntryPoint.commence(httpRequest, httpResponse, authenticationException);
		}
	}

}
