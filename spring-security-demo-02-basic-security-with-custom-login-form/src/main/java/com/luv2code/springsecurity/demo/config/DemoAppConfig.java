package com.luv2code.springsecurity.demo.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.luv2code.springsecurity.demo")
@PropertySource("classpath:persistence-mysql.properties")
// @PropertySource("classpath: persistence-mysql.properties") this is for database. 
// persistence-mysql.properties is under resources folder.
public class DemoAppConfig {
	
	// this set up is for database connectivity to read username and password from the database
	// set up variable to hold the properties
	@Autowired
	private Environment env;
	// the above variable will hold data read from properties files
	
	// set up logger for diagnostics
	private Logger logger = Logger.getLogger(getClass().getName());
	

	// define a bean for viewResolver. This used to be done inside
	// "spring-mvc-crud-demo-servlet.xml" file. But now we are doing it inside java configuration file. 

	@Bean 
	public ViewResolver viewResolver() {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}
	
	// define a bean for security datasource. 
	// This is all pure java configuration for setting up data source, all java code, no xml
	@Bean
	public DataSource securityDataSource() {
		
		// create connection pool
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
		
		
		// set the jdbc driver class. we need to surround this with try/catch
		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
			// jdbc.driver is found inside persistence-mysql.properties
			// " env.getProperty("jdbc.driver") " will read database configs from "persistence-mysql.properties" file
		} catch (PropertyVetoException exc) {
			throw new RuntimeException(exc);
		}
		
		// log the connection props, this is just to make sure we are reading the correct information from the properties file
		logger.info("**** jdbc.url= " + env.getProperty("jdbc.url") + " ****");
		logger.info("**** jdbc.user= " + env.getProperty("jdbc.user") + " ****");
		
		// set database connection props
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url")); 
		securityDataSource.setUser(env.getProperty("jdbc.user")); 
		securityDataSource.setPassword(env.getProperty("jdbc.password")); 
		
		
		// set connection pool props. call the getIntProperty method (see below) to convert String to int
		// see "persistence-mysql.properties" file
		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
		
		return securityDataSource;
	}
	
	// the following method will convert property values from String to integer. 
	// property values come as a String. Instead of converting them everywhere, call this method and pass the string to get int. 
	private int getIntProperty(String propName) {
		
		String propValue = env.getProperty(propName);
		
		// now convert to int
		int intPropValue = Integer.parseInt(propValue);
		
		return intPropValue;
	}
	
}
