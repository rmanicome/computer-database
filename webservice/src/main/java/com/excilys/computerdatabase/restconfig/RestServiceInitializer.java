package com.excilys.computerdatabase.restconfig;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import com.excilys.computerdatabase.configuration.Application;
import com.excilys.computerdatabase.restconfig.WebServiceConfig;

public class RestServiceInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { Application.class };
    }
  
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebServiceConfig.class };
    }
  
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
