package com.fprochazka.drill.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Michaela Bamburová on 15.05.2016.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.fprochazka.drill" })
public class ApplicationConfig {

}
