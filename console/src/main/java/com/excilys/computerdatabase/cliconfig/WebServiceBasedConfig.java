package com.excilys.computerdatabase.cliconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.excilys.computerdatabase.restconfig")
@Import(JacksonConfig.class)
public class WebServiceBasedConfig {
	
}
