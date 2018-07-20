package com.excilys.computerdatabase.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.excilys.computerdatabase.mapper",
		"com.excilys.computerdatabase.validator",
		"com.excilys.computerdatabase.service",
		"com.excilys.computerdatabase.persistence"})
public class Application {
	
}
