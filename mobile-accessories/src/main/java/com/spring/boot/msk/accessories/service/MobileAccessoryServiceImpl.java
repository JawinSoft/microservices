package com.spring.boot.msk.accessories.service;

import static org.springframework.util.StringUtils.isEmpty;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.msk.accessories.dto.SaveMobileAccessoryRequest;
import com.spring.boot.msk.accessories.exception.MobileAccessoryNotFoundException;
import com.spring.boot.msk.accessories.repository.MobileAccessoriesRepository;
import com.spring.boot.msk.common.model.MobileAccessory;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class MobileAccessoryServiceImpl implements MobileAccessoryService {

	
	@Autowired
	private MobileAccessoriesRepository mobileAccessoriesRepository;
	
	@Override
	public Mono<MobileAccessory>  saveMobileAccessory(SaveMobileAccessoryRequest request) {
		MobileAccessory model = converyDtoToModel(request);
		return mobileAccessoriesRepository.save(model);
	}
	
	
	private MobileAccessory converyDtoToModel(SaveMobileAccessoryRequest request) {
		return MobileAccessory
						.builder()
						.id(UUID.randomUUID().toString())
						.name(request.getName())
						.description(request.getDescription())
						.mobileType(isEmpty(request.getMobileType()) ?   "ANY" : request.getMobileType())
						.build();
	}


	@Override
	public Flux<MobileAccessory> getAllMobileAccessories(String mobileType) {
		return mobileAccessoriesRepository.findByMobileType(mobileType);
	}


	// RuntimeException --> Custom Exception
	//
	@Override
	public Mono<MobileAccessory> getMobileAccessoryByUUID(String mobileAccessoryCode) {
		 Mono<MobileAccessory>  mobileAccessory =	mobileAccessoriesRepository
				 									.findById(mobileAccessoryCode)
				 									.switchIfEmpty(createNotFoundError());
		 return mobileAccessory;
	}
	
	
	
	private <R> Mono<R> createNotFoundError() {
		return Mono.defer(() -> {
			Exception ex = new MobileAccessoryNotFoundException("Mobile Accessory Not Found with Given Code");
			return Mono.error(ex);
		});
	}

}
