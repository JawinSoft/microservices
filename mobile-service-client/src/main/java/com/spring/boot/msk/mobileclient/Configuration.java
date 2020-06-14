package com.spring.boot.msk.mobileclient;

import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.reactive.function.client.WebClient;

@org.springframework.context.annotation.Configuration
public class Configuration {

	@Value("${mobile-service-base-url}")
	private String mobileServiceBaseUrl;
	
	@Value("${country-service-base-url}")
	private String countryServiceBaseUrl;
	
	
	@Value("${mobile-accessory-service-base-url}")
	private String mobileAccessoryServiceBaseUrl;
	
	
	@Bean
	public WebClient mobileServiceWebClient() {	
		return WebClient
					.builder()
					.filter(basicAuthentication("user", "user123"))
					.baseUrl(mobileServiceBaseUrl).build();		
	}
	
	
	@Bean
	public WebClient countryServiceWebClient() {		
		return WebClient
				.builder()
				.filter(basicAuthentication("country_user", "country_user"))
				.baseUrl(countryServiceBaseUrl).build();		
	}
	
	@Bean
	public WebClient mobileAccessoryServiceWebClient() {	
		return WebClient.builder().baseUrl(mobileAccessoryServiceBaseUrl).build();		
	}
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
