package com.spring.boot.msk.accessories;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.spring.boot.msk.accessories.repository.MobileAccessoriesRepository;
import com.spring.boot.msk.accessories.service.MobileAccessoryService;
import com.spring.boot.msk.accessories.service.MobileAccessoryServiceImpl;
import com.spring.boot.msk.common.model.MobileAccessory;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest(classes = MobileAccessoryServiceImpl.class) 
public class MobileAccessoryServiceTest {
	
	@MockBean
	private MobileAccessoriesRepository repository;
	
	@Autowired
	private MobileAccessoryService service;
	
	@Test
	public void testGetMobileAccessoryByUUID() {
		MobileAccessory ma = MobileAccessory.builder().build();
		when(repository.findById(any(String.class).toString())).thenReturn(Mono.just(ma));
		
		Mono<MobileAccessory> response = service.getMobileAccessoryByUUID("abc");
		
		StepVerifier.create(response)
		.expectNextMatches(ms -> ma.getDescription()== null)
		.expectComplete()
		.verify();
		
	}
	

}
