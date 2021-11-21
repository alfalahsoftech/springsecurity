package com.alfalahsoftech.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
//@Configuration  //@SpringBootApplication is a convenience annotation that add this annotation
public class AuthConfigurer extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("userDetailsService = "+userDetailsService.getClass().getName());
		auth.userDetailsService(userDetailsService);
	}
	//if we comment below we get below exception
	//java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"

	@Bean 
	public PasswordEncoder passwordEncoder() { 
		System.out.println("AuthConfigurer.passwordEncoder()");
		return	  NoOpPasswordEncoder.getInstance(); 
	}
	
	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.csrf().disable().authorizeRequests() .antMatchers("/") .permitAll()
	 * .anyRequest() .authenticated() .and() .httpBasic(); }
	 */
	
	//Caused by: java.lang.IllegalArgumentException: role should not start with 'ROLE_' 
	//since it is automatically inserted. Got 'ROLE_USER'
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers("/")
		.permitAll()
		.antMatchers("/home").hasAuthority("USER")
		.antMatchers("/admin").hasAuthority("ADMIN") 
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic()
		.and()
		.logout().invalidateHttpSession(true)
		.clearAuthentication(true).permitAll();
		
	}
	
	

}
