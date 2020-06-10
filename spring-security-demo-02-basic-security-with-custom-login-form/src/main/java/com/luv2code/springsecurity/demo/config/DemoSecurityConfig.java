package com.luv2code.springsecurity.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
	// we create this class using superclass as WebSecurityConfigurerAdapter
	// then we need to override methods. sourse --> Override/implement Methods --> choose the methods below!
	
	
	// add a reference to security data source
			@Autowired
			private DataSource securityDataSource;

	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		
		// using the following code, we can avoid hard-coding users. to see manual set up see spring notes, search for spring security
		// the following code tell spring security to use JDBC authentication with our data source
		// see DemoAppConfig.java file to see the complete no-xml java only configuration!!
		auth.jdbcAuthentication().dataSource(securityDataSource);
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
