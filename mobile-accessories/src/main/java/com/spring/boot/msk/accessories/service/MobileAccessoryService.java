package com.spring.boot.msk.accessories.service;

import com.spring.boot.msk.accessories.dto.SaveMobileAccessoryRequest;
import com.spring.boot.msk.accessories.model.MobileAccessory;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MobileAccessoryService {

	Mono<MobileAccessory> saveMobileAccessory(SaveMobileAccessoryRequest request);

	Flux<MobileAccessory> getAllMobileAccessories();

	Mono<MobileAccessory> getMobileAccessoryByUUID(String mobileAccessoryCode);
	
}
