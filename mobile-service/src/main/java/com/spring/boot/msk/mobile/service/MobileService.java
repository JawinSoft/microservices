package com.spring.boot.msk.mobile.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.msk.mobile.dto.MobileDTO;
import com.spring.boot.msk.mobile.dto.SaveMobileRequest;
import com.spring.boot.msk.mobile.dto.UpdateMobileRequest;
import com.spring.boot.msk.mobile.entity.Lob;
import com.spring.boot.msk.mobile.entity.Mobile;
import com.spring.boot.msk.mobile.entity.Status;
import com.spring.boot.msk.mobile.exception.MobileNotFoundException;
import com.spring.boot.msk.mobile.repository.MobileRepository;

@Service
public class MobileService {

	
	@Autowired
	private MobileRepository mobileRepository;	
	

	public MobileDTO getMobileById(int mobileId) {
		Optional<Mobile> mobile =  mobileRepository.findById(mobileId);
		if(!mobile.isPresent()) {
			throw new MobileNotFoundException(mobileId);
		}
		Mobile dbMobile=  mobile.get();
		
		return convertEntityToDTO(dbMobile);
		
	}

	public List<MobileDTO> getMoblies() {
		Iterable<Mobile> dbMobiles= mobileRepository.findAll();
		
		 List<MobileDTO> mobiles = new ArrayList<>();
		 
		 dbMobiles.forEach( dbMobile -> mobiles.add( convertEntityToDTO(dbMobile)));
		 
		 return mobiles;
	}


	public MobileDTO save(SaveMobileRequest saveMobile) {

    	Mobile mobile = Mobile.builder()
				.name(saveMobile.getName())
				.countryCode(saveMobile.getCountryCode())
				.lob(Lob.valueOf(saveMobile.getLob()))
				.status(Status.valueOf(saveMobile.getStatus()))
				.price(saveMobile.getPrice())
				.build();
    	mobileRepository.save(mobile);
    	
    	return convertEntityToDTO(mobile);
		
	}

	
	public void deleteMobile(int mobileId) {
		Optional<Mobile> mobile =  mobileRepository.findById(mobileId);
		if(!mobile.isPresent()) {
			throw new MobileNotFoundException(mobileId);
		}
		
		mobile.get().setIsActive(false);
		
		mobileRepository.save(mobile.get());
	}
	

	public Mobile update(UpdateMobileRequest updateMobile) {
		Mobile mobile = Mobile.builder()
				.id(updateMobile.getId())
				.name(updateMobile.getName())
				.countryCode(updateMobile.getCountryCode())
				.lob(Lob.valueOf(updateMobile.getLob()))
				.status(Status.valueOf(updateMobile.getStatus()))
				.price(updateMobile.getPrice())
				.build();
		
		return mobileRepository.save(mobile);
		
		
	}

	
	
	private MobileDTO convertEntityToDTO(Mobile mobile) {
		return MobileDTO.builder()
				.id(mobile.getId())
				.countryCode(mobile.getCountryCode())
				.lob(mobile.getLob().name())
				.name(mobile.getName())
				.status(mobile.getStatus().name())
				.publictionDate(mobile.getPublicationDate().toString())
				.price(mobile.getPrice())
				.build();
		
	}
}
