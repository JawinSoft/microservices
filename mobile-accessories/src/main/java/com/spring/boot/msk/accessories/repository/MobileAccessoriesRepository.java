package com.spring.boot.msk.accessories.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.msk.common.model.MobileAccessory;

import reactor.core.publisher.Flux;

@Repository
public interface MobileAccessoriesRepository extends ReactiveMongoRepository<MobileAccessory, String> {

	Flux<MobileAccessory> findByMobileType(String mobileType);
	
}
