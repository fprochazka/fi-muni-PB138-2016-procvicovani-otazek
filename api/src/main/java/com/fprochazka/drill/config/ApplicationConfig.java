package com.fprochazka.drill.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.fprochazka.drill")
@ImportResource(locations = "application-context.xml")
@ServletComponentScan
@EnableMongoRepositories(basePackages = "com.fprochazka.drill")
public class ApplicationConfig
{

	@Bean
	public WebMvcConfigurer corsConfigurer()
	{
		return new WebMvcConfigurerAdapter()
		{
			@Override
			public void addCorsMappings(CorsRegistry registry)
			{
				registry
					.addMapping("/**")
					.allowedOrigins("http://localhost:9000", "chrome-extension://fdmmgilgnpjigdojojpjoooidkmcomcm");
			}
		};
	}

}
