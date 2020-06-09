package com.luv2code.springsecurity.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
	// we create this class using superclass as WebSecurityConfigurerAdapter
	// then we need to override methods. sourse --> Override/implement Methods --> choose the methods below!
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// super.configure(auth);

		// add users for in-memory authentication
		UserBuilder users = User.withDefaultPasswordEncoder();

		auth.inMemoryAuthentication()
				.withUser(users.username("teddy").password("test123").roles("EMPLOYEE"))
				.withUser(users.username("melkam").password("test123").roles("EMPLOYEE", "MANAGER"))
				.withUser(users.username("kalkidan").password("test123").roles("EMPLOYEE", "ADMIN"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				/*
				 * .anyRequest() 
				 * .authenticated()
				 */
		// .anyRequest().authenticated() gives access to those who are able to use their username and password
		// if we want to filter based on specific access for a specific role, we use .antMatchers().hasRoles() as follows!
			.antMatchers("/").hasRole("EMPLOYEE") // all employees have access to landing page!
			.antMatchers("/leaders/**").hasRole("MANAGER") // " leaders/** " means leaders and its sub-folders/files
			.antMatchers("/systems/**").hasRole("ADMIN")
			.and()
			.formLogin()
			.loginPage("/showMyLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.permitAll()
				// above this line is login support and below this line is logout support. It
				// will be appended using (.)
				.and()
				.logout().permitAll()
		// permitAll allows everyone to see the login/logout form/message.		
				.and()
				.exceptionHandling().accessDeniedPage("/access-denied");
		// .exceptionHandling().accessDeniedPage(); this will allow us to build our own access denied page instead of 403, 
		// forbidden page!
	}

}
