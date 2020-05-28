package com.spring.boot.msk.accessories.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.msk.accessories.dto.SaveMobileAccessoryRequest;
import com.spring.boot.msk.accessories.model.MobileAccessory;
import com.spring.boot.msk.accessories.service.MobileAccessoryService;
import com.spring.boot.msk.common.dto.Response;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("mobile-accessory")
@Slf4j
public class MobileAccessoryController {
	
	//Logger log  = LoggerFactory.getLogger(MobileAccessoryController.class);
	
	@Autowired
	private MobileAccessoryService mobileAccessoryService;

	@PostMapping
	public Mono<Response> saveMobileAccessory(@RequestBody @Valid SaveMobileAccessoryRequest request) {
		Mono<MobileAccessory> savedMobileAccessoryMono =  mobileAccessoryService.saveMobileAccessory(request);
		
		log.info("1111 "+Thread.currentThread().getName());
		
		return savedMobileAccessoryMono.map(savedMobileAccessory -> {
			Response finalResponse =  Response.builder().response(savedMobileAccessory).build();
			log.info("222 "+Thread.currentThread().getName());
			return finalResponse;	
		});
		
		
	}
	
	@GetMapping
	public Mono<Response> getAllMobileAccessories(){
		Flux<MobileAccessory> allMobileAccessoriesFlux = mobileAccessoryService.getAllMobileAccessories();
		
		log.info("1111 "+Thread.currentThread().getName());
		
		return allMobileAccessoriesFlux
				.collectList()
				.map(allMobileAccessories -> {
					
					log.info("2222 "+Thread.currentThread().getName());
					
				Response finalResponse = Response.builder().response(allMobileAccessories).build();
				return finalResponse;
				
		});
	}
	
	@GetMapping("{mobile-accessory-code}")
	public Mono<Response> getMobileAccessoryByUUID(@PathVariable("mobile-accessory-code") String mobileAccessoryCode){
		 return mobileAccessoryService
				 .getMobileAccessoryByUUID(mobileAccessoryCode)
		 		 .map(mobileAccessory ->  Response.builder().response(mobileAccessory).build());
	}
	
	
	
	
}

