package com.spring.boot.msk.mobile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration

public class MobileServiceConfig {

	/*
	 * @Bean public Docket api() { return new Docket(DocumentationType.SWAGGER_2)
	 * .select() .apis(RequestHandlerSelectors.any())
	 * .paths(PathSelectors.ant("/mobiles" )) .build(); }
	 */
	
	@Bean
	public OpenAPI customOpenAPI() {
	return new OpenAPI().components(new Components()).
		info(new Info().title("Contact Application API")
		.description("This is a sample Spring Boot RESTful "
		+ "service using springdoc-openapi and OpenAPI 3."));
	}
}
