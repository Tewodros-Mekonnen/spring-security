package com.luv2code.springsecurity.demo.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// this class is performs the same as what we used to in web.xml. It handles servlet and servlet-mapping
public class MySpringMvcDispatcherServletInitilizer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// no root config classes for my project...only Servlet config classes.
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {

		return new Class[] { DemoAppConfig.class }; // this is the class we created "DemoAppConfig.java"
	}

	@Override
	protected String[] getServletMappings() {

		return new String[] { "/" };
	}

}
