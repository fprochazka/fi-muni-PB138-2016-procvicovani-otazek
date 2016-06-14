package com.fprochazka.drill.cli;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.fprochazka.drill")
public class QuestionLoaderCommand
{
	public static void main( String[] args )
	{
		SpringApplication.run( QuestionLoaderCommand.class, args ).close();
	}
}
