package com.expense.tracker.cloudgatewy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("reached here ");
		http.authorizeRequests().anyRequest().authenticated().and().oauth2Login().and()
        .csrf().disable();
		
		/*
		 * http.authorizeRequests().antMatchers("/login").permitAll().antMatchers(
		 * "/oauth/token/revokeById/**").permitAll()
		 * .antMatchers("/tokens/**").permitAll().anyRequest().authenticated().and().
		 * formLogin().permitAll().and() .csrf().disable();
		 */
	}
	
}
