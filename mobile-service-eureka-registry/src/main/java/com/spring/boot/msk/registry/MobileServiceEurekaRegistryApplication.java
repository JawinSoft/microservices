package com.spring.boot.msk.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MobileServiceEurekaRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileServiceEurekaRegistryApplication.class, args);
	}

}
