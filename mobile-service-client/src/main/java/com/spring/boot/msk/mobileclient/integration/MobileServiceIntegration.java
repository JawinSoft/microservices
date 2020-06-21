package com.spring.boot.msk.mobileclient.integration;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.spring.boot.msk.common.client.dto.MobileInfo;

import com.spring.boot.msk.common.dto.MobileDTO;
import com.spring.boot.msk.common.dto.Response;
import com.spring.boot.msk.common.model.Country;
import com.spring.boot.msk.common.model.MobileAccessory;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Service
@Slf4j
public class MobileServiceIntegration {

	@Autowired
	private WebClient mobileServiceWebClient;
	
	
	@Autowired
	private WebClient countryServiceWebClient;
	
	@Autowired
	private WebClient mobileAccessoryServiceWebClient;
	
	
	
	public Mono<MobileInfo> getMobileInfo(int mobileId) {
		
		
		Mono<Response<MobileDTO>> mobileServiceResponse=	
										mobileServiceWebClient
											.get()
											.uri("/{id}", mobileId)
											.accept(MediaType.APPLICATION_JSON)
											.retrieve()
											.bodyToMono(new ParameterizedTypeReference<Response<MobileDTO>>() {});
							
		return  mobileServiceResponse.flatMap(response -> processMobileInfo(response.getResponse()));
		
		//return monoMobileInfo.flatMap(mobileInfo ->  Mono.just(MobileServiceResponse.builder().mobiles(Arrays.asList(mobileInfo)).build()));
		
	}
	
		
		
		
	
	private Mono<Response<Country>>  getCountryDetailsByCountryCode(String countryCode) {
		log.info("Making Country Service Call");
		return countryServiceWebClient
				   .get()
				   .uri("/{id}", countryCode)
				   .accept(MediaType.APPLICATION_JSON)
				   .retrieve()
				    .bodyToMono(new ParameterizedTypeReference<Response<Country>>() {});
	}
	
	
	private Mono<Response<List<MobileAccessory>>>  getAllMobileAccessoriesByCode(String mobileType) {
		log.info("Making MobileAccessory Service Call");
		return
				mobileAccessoryServiceWebClient
							.get()
							.uri("/all/"+mobileType)
							.accept(MediaType.APPLICATION_JSON)
							.retrieve()
							.bodyToMono(new ParameterizedTypeReference<Response<List<MobileAccessory>>>() {} );
	}
	
	private Mono<MobileInfo> processMobileInfo(MobileDTO mobileDto) {
		
		Mono<Response<Country>> countrySericeResponse = getCountryDetailsByCountryCode(mobileDto.getCountryCode());
		
		Mono<Response<List<MobileAccessory>>> mobilesAccessories = getAllMobileAccessoriesByCode("ANY");
										
		
		Mono<Tuple2<Response<Country>, Response<List<MobileAccessory>>>> zipped =	Mono.zip(countrySericeResponse, mobilesAccessories);
		
		return	zipped.flatMap(tuple -> {
				
				Response<Country> countryServiceResponse = tuple.getT1();
				Response<List<MobileAccessory>> mobileAccessoryServiceResponse = tuple.getT2();
				
				MobileInfo mobileInfo = MobileInfo.builder().id(mobileDto.getId())
															.lob(mobileDto.getLob())
															.name(mobileDto.getName())
															.price(mobileDto.getPrice())
															.publictionDate(mobileDto.getPublictionDate())
															.serverPort(mobileDto.getServerPort())
															.status(mobileDto.getStatus()).build();
										
				
				mobileInfo.setCountry(countryServiceResponse.getResponse());
				
				mobileInfo.setMobileAccessories(mobileAccessoryServiceResponse.getResponse());
				
				
				
				
				return Mono.just(mobileInfo);
				
			});		
	}



	public Flux<MobileInfo> getAllMobileInfo() {
		
		Mono<Response<List<MobileDTO>>> mobileServiceResponse=	
														mobileServiceWebClient
															.get()					
															.accept(MediaType.APPLICATION_JSON)
															.retrieve()
															.bodyToMono(new ParameterizedTypeReference<Response<List<MobileDTO>>>() {});
		
		return mobileServiceResponse.flatMapMany(response -> {
			
			List<MobileDTO> mobiles = response.getResponse();//List of Mobiles from Mobile -Service
			
			return Flux.fromIterable(mobiles).flatMap(mobile ->{
				
				return processMobileInfo(mobile); //Mono - MobileInfo
				
			});//Flux 
			
		});
	
		
		
		
		
	}	
	
	
}
