package com.fprochazka.drill.cli;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class QuestionLoaderCommand
{
	public static void main( String[] args )
	{
		SpringApplication.run( QuestionLoaderCommand.class, args ).close();
	}
}
