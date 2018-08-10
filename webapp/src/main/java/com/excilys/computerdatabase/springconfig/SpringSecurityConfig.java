package com.excilys.computerdatabase.springconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.excilys.computerdatabase.controller.ControllerConstant;
import com.excilys.computerdatabase.model.ConstantDB;
import com.excilys.computerdatabase.service.UserService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserService userService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers(new String[]{}).permitAll()
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage(ControllerConstant.HOME+ControllerConstant.LOGIN)
			.passwordParameter(ConstantDB.USER_PASSWORD)
			.usernameParameter(ConstantDB.USER_NAME)
			.defaultSuccessUrl(ControllerConstant.HOME+ControllerConstant.DASHBOARD)
			.permitAll()
			.and()
		.logout()                                    
			.permitAll()
			.logoutSuccessUrl("/login?signedout=true");
	}

	@Bean
	public AuthenticationProvider getProvider() {
		AuthenticationProvider provider = new AuthenticationProvider();
		provider.setUserDetailsService(userService);
		return provider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
