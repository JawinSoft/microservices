package com.spring.boot.msk.mobileclient;

import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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
		
		//ServiceInstance service = discoveryClient.getInstances("mobile-service").get(0);
		//URI uri = service.getUri();
		
		//System.out.println("uri "+uri);
		
		
		return WebClient
					.builder()
					.filter(basicAuthentication("user", "user123"))
					.baseUrl(mobileServiceBaseUrl).build();		
	}
	
	
	@Bean
	public WebClient countryServiceWebClient() {	
		//ServiceInstance service = discoveryClient.getInstances("country-service").get(0);
		//URI uri = service.getUri();
		//System.out.println("uri "+uri);
		//System.out.println("uri "+uri);
		return WebClient
				.builder()
				.filter(basicAuthentication("country_user", "country_user"))
				.baseUrl(countryServiceBaseUrl).build();		
	}
	
	@Bean
	public WebClient mobileAccessoryServiceWebClient() {	
		//ServiceInstance service = discoveryClient.getInstances("mobile-accessory").get(0);
		//URI uri = service.getUri();
		//System.out.println("uri "+uri);
		
		return WebClient.builder().baseUrl(mobileAccessoryServiceBaseUrl).build();		
	}
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
