package com.excilys.computerdatabase.springconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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
	
	@Autowired
	protected void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
		auth.authenticationProvider(getProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
		.and()
		.authorizeRequests()
			.antMatchers(new String[]{ "/css/**", "/js/**", "/images/**", ControllerConstant.HOME+ControllerConstant.LOGIN+"*" }).permitAll()
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
			.logoutSuccessUrl(ControllerConstant.HOME+ControllerConstant.LOGIN+"?signedout=true");
	}

	@Bean
	public AuthenticationProvider getProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userService);
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
