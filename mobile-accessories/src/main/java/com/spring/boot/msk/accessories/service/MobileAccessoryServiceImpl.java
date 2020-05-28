package com.spring.boot.msk.accessories.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import com.spring.boot.msk.accessories.dto.SaveMobileAccessoryRequest;
import com.spring.boot.msk.accessories.exception.MobileAccessoryNotFoundException;
import com.spring.boot.msk.accessories.model.MobileAccessory;
import com.spring.boot.msk.accessories.repository.MobileAccessoriesRepository;
import static org.springframework.util.StringUtils.isEmpty;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import static java.util.Objects.isNull;


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
	public Flux<MobileAccessory> getAllMobileAccessories() {
		return mobileAccessoriesRepository.findAll();
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
