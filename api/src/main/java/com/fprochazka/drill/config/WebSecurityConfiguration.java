package com.fprochazka.drill.config;

import com.fprochazka.drill.model.authentication.AuthenticationProvider;
import com.fprochazka.drill.model.authentication.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter
{

	@Autowired
	JwtAuthenticationFilter jwtAuthenticationFilter;

	@Override
	public void configure(HttpSecurity http) throws Exception
	{
		http
			.httpBasic()
				.disable()
			.csrf()
				.disable()
			.anonymous()
				.disable()
			.addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter.class)
		;
	}

}
