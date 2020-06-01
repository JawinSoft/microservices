package com.spring.boot.msk.common.config;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.spring.boot.msk.common.aop.LogMethodExecutionTimeAspect;
import com.spring.boot.msk.common.exception.CommonGlobalExceptionHandler;
import com.spring.boot.msk.common.filter.LoggingFilter;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class CommonConfiguration {
	
	@Value(value = "${application-name}")
	private String applicationame;
	
	
	@Value(value = "${description}")
	private String description;
	
	@Bean
	public OpenAPI customOpenAPI() {
	return new OpenAPI().components(new Components()).
		info(new Info().title(applicationame)
		.description(description));
	}
	
	@Bean
	public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:error-messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
    
    @Bean
    public LoggingFilter loggingFilter() {
    	return new LoggingFilter();
    }
    

    @Bean
    public FilterRegistrationBean<Filter> registerFilters(){
    	FilterRegistrationBean<Filter> filterConfig = new FilterRegistrationBean<>();
    	filterConfig.setFilter( loggingFilter() );
    	filterConfig.addUrlPatterns("/*");    	
    	
    	return filterConfig;
    }
    
    
    @Bean 
    public CommonGlobalExceptionHandler commonGlobalExceptionHandler() {
    	return new CommonGlobalExceptionHandler();
    }
    
    
    @Bean
    public LogMethodExecutionTimeAspect logMethodExecutionTimeAspect() {
    	return new LogMethodExecutionTimeAspect();
    }

}
