package com.fprochazka.drill.config;

import com.fprochazka.drill.model.api.JwtFilter;
import com.fprochazka.drill.model.api.JwtProperties;
import com.fprochazka.drill.model.authentication.password.PasswordAuthenticationFacade;
import com.fprochazka.drill.model.authentication.password.PasswordAuthenticatorService;
import com.fprochazka.drill.model.authentication.password.PasswordEncoderProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.DispatcherType;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.fprochazka.drill")
@ImportResource(locations = "application-context.xml")
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
					.allowedOrigins("http://localhost:9000", "chrome-extension://fdmmgilgnpjigdojojpjoooidkmcomcm");
			}
		};
	}

	@Bean
	@Autowired
	public FilterRegistrationBean jwtFilter(PasswordAuthenticatorService passwordAuthenticatorService)
	{
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter(passwordAuthenticatorService));
		registrationBean.addUrlPatterns("/*");
		registrationBean.setName("jwt");
		registrationBean.setDispatcherTypes(DispatcherType.REQUEST);
		return registrationBean;
	}

	@Bean
	@Autowired
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
