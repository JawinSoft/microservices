package com.spring.boot.msk.accessories.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.msk.accessories.model.MobileAccessory;

@Repository
public interface MobileAccessoriesRepository extends ReactiveMongoRepository<MobileAccessory, String> {

}
