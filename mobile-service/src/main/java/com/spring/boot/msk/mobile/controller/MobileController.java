package com.spring.boot.msk.mobile.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.msk.common.dto.MobileDTO;
import com.spring.boot.msk.common.dto.Response;
import com.spring.boot.msk.common.dto.SaveMobileRequest;
import com.spring.boot.msk.common.dto.UpdateMobileRequest;
import com.spring.boot.msk.mobile.service.MobileService;



@RestController
@RequestMapping("mobiles")
public class MobileController {
	
	@Autowired
	private MobileService mobileService;
	
	@Autowired
	private Environment env;
	
	
	@GetMapping("{mobileId}")//{mobileId} --> Path Variable
	public Response<MobileDTO> getMobileById(@PathVariable("mobileId") int id) {
		
		String port = env.getProperty("local.server.port");
		
		
		MobileDTO mobileDTO= mobileService.getMobileById(id);
		mobileDTO.setServerPort(port);
		return Response.<MobileDTO>builder().response(mobileDTO).build();
	}
	
	/**
	 * Method to add a new Mobile into existing mobiles List
	 * @param mobile
	 * @return
	 */
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public  Response<MobileDTO> saveMobile(@RequestBody @Valid SaveMobileRequest saveMobile) {		
		MobileDTO mobileDTO=  mobileService.save(saveMobile); 
		return Response.<MobileDTO>builder().response(mobileDTO).build();
	}
	
	@GetMapping
	public Response<List<MobileDTO>> getAllMobiles(){
		List<MobileDTO> mobilesDtos =  mobileService.getMoblies();
		
		return Response.<List<MobileDTO>>builder().response(mobilesDtos).build();
	}
	
	@DeleteMapping("{mobile-id}")
	public Response<Boolean> deleteMobileById(@PathVariable("mobile-id") int mobileId) {
		mobileService.deleteMobile(mobileId);
		return Response.<Boolean>builder().response(true).build();
	}
	
	@PutMapping
	public  Response<Boolean> updateMobile(@RequestBody @Valid UpdateMobileRequest mobile) {
		 mobileService.update(mobile); 
		 return Response.<Boolean>builder().response(true).build();
	}


}
