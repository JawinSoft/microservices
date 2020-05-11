package com.spring.boot.msk.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.RequestPredicate;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RouterFunctions.route;

import static org.springframework.web.servlet.function.RequestPredicates.GET;
import static org.springframework.web.servlet.function.RequestPredicates.accept;

import com.spring.boot.msk.mobile.handler.MobileHandler;
import com.spring.boot.msk.mobile.service.MobileService;

@SpringBootApplication
public class MobileServiceFunctionalApplication {
	
	@Autowired
	private MobileHandler mobileHandler;
	
	@Autowired
	private MobileService mobileService;
	

	public static void main(String[] args) {
		SpringApplication.run(MobileServiceFunctionalApplication.class, args);
	}

	
	
	@Bean
	public RouterFunction<ServerResponse> routerFunction() {
		
		return route()
				.GET("/get-all-mobiles", accept(MediaType.APPLICATION_JSON), request ->
				
				        ServerResponse.ok().body(mobileService.getMoblies())
				
					)
				//.GET("/get-mobile-byId", accept(MediaType.APPLICATION_JSON), mobileHandler::getMobileById)
				.build()
				;
		      
	}
	
}
