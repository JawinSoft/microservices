package com.spring.boot.msk.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class LoggingFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		
		long startTime = System.currentTimeMillis();
		
		chain.doFilter(request, response);
		
		String url = ((HttpServletRequest)request).getRequestURL().toString();
		log.info("-");
		
		log.info("The total Time taken to Process URI  "+url+" Time  : "+ ( System.currentTimeMillis() - startTime) );
		log.info("-");
	}

	
	
}
