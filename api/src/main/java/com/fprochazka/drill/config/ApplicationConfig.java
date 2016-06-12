package com.fprochazka.drill.config;

import com.fprochazka.drill.model.authentication.JwtProperties;
import com.fprochazka.drill.model.authentication.password.PasswordEncoderProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.*;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.fprochazka.drill")
@ImportResource(locations = "application-context.xml")
@Import(WebSecurityConfiguration.class)
@ServletComponentScan

@EnableMongoRepositories(basePackages = "com.fprochazka.drill")
@EnableConfigurationProperties({PasswordEncoderProperties.class, JwtProperties.class})
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
					.allowedOrigins("http://localhost:3000", "chrome-extension://fdmmgilgnpjigdojojpjoooidkmcomcm");

			}
		};
	}

	public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory)
	{
		return new MongoTemplate(mongoDbFactory);
	}

	@Bean
	@Autowired
	public PasswordEncoder passwordEncoder(@Value("${password-encoder.bcrypt.strength}") int strength)
	{
		return new BCryptPasswordEncoder(strength);
	}
}
