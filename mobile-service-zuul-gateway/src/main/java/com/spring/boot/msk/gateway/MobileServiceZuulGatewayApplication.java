package com.spring.boot.msk.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class MobileServiceZuulGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileServiceZuulGatewayApplication.class, args);
	}

}
