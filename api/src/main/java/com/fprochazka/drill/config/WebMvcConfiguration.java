package com.fprochazka.drill.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Service
public class WebMvcConfiguration extends WebMvcConfigurerAdapter
{

	@Value("${api.client.origin}")
	String clientOrigin;

	@Override
	public void addCorsMappings(CorsRegistry registry)
	{
		registry
			.addMapping("/**")
			.allowedOrigins(clientOrigin);
	}

}
