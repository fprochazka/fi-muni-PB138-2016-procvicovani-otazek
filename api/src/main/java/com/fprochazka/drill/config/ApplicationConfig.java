package com.fprochazka.drill.config;

import com.fprochazka.drill.model.authentication.JwtProperties;
import com.fprochazka.drill.model.authentication.password.PasswordEncoderProperties;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.*;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

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

}
