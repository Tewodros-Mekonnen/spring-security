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
				.withUser(users.username("teddy").password("test123").roles("EMPLOYEE", "HR-ADMIN"))
				.withUser(users.username("melkam").password("test123").roles("EMPLOYEE", "MANAGER"))
				.withUser(users.username("kalkidan").password("test123").roles("EMPLOYEE", "ADMIN"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/showMyLoginPage")
				.loginProcessingUrl("/authenticateTheUser").permitAll()
				// above this line is login support and below this line is logout support. It
				// will be appended using (.)
				.and().logout().permitAll();
		// permitAll allows everyone to see the login/logout form/message.
	}

}
