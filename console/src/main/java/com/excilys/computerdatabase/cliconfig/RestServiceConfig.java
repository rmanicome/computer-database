package com.excilys.computerdatabase.cliconfig;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.excilys.computerdatabase.configuration.Application;

public class RestServiceConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { Application.class };
    }
  
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebServiceBasedConfig.class };
    }
  
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}