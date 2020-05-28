package com.spring.boot.msk.accessories;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.spring.boot.msk.common.config.CommonConfiguration;

@SpringBootApplication
@Import(value = CommonConfiguration.class)
public class MobileAccessoriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileAccessoriesApplication.class, args);
	}
	
	
}
