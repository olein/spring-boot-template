package com.jonak.template.configuration;

import org.hibernate.annotations.Filter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.Logger;

/*
* Custom filter added for user authentication
* Users will be authenticated first using DB configurations
* and if authenticated then will be forwared to access the api.
* */
@Filter(name = "customFilter")
public class CustomFilter extends SecurityContextPersistenceFilter {

    private static Logger log = Logger.getLogger(CustomFilter.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        log.info("In custom filter start");
        int contextPath = ((HttpServletRequest) request).getContextPath().length();
        log.info("Context path URL : " + ((HttpServletRequest) request).getContextPath());
        log.info("Request URL : " + ((HttpServletRequest) request).getRequestURI().substring(contextPath));
        chain.doFilter(request, response);
        log.info("In custom filter end");
    }
}
