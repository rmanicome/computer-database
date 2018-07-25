package com.excilys.computerdatabase.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.excilys.computerdatabase.controller.ControllerConstant;

public class ConfigServletsContextSpring extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class [] {Application.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class [] {SpringMVCConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {ControllerConstant.HOME};
	}
}
