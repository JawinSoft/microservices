package com.spring.boot.msk.mobileclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.msk.common.client.dto.MobileInfo;
import com.spring.boot.msk.mobileclient.integration.MobileServiceIntegration;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("mobile-client")
public class MobileClientController {

	@Autowired
	private MobileServiceIntegration mobileServiceIntegration;
	
	@GetMapping("{mobile-id}")
	public Mono<MobileInfo> getMobileInfo(@PathVariable("mobile-id") int mobileId){
		
		return mobileServiceIntegration.getMobileInfo(mobileId);
		
	}
	
	@GetMapping
	public Flux<MobileInfo> getAllMobileInfo(){
		
		return mobileServiceIntegration.getAllMobileInfo();
		
	}
	
	@GetMapping("/hi")
	public String sayHello() {
		return "Hello My Dear Friend Welcome to Spring Security with Json Web Token..!";
	}
	
	
}
