package com.spring.boot.msk.mobile.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.spring.boot.msk.common.dto.Response;
import com.spring.boot.msk.mobile.dto.MobileDTO;
import com.spring.boot.msk.mobile.dto.SaveMobileRequest;
import com.spring.boot.msk.mobile.dto.UpdateMobileRequest;
import com.spring.boot.msk.mobile.service.MobileService;



@RestController
@RequestMapping("mobiles")
public class MobileController {
	
	@Autowired
	private MobileService mobileService;
	
	@GetMapping("{mobileId}")//{mobileId} --> Path Variable
	public Response getMobileById(@PathVariable("mobileId") int id) {
		MobileDTO mobileDTO= mobileService.getMobileById(id);
		return Response.builder().response(mobileDTO).build();
	}
	
	/**
	 * Method to add a new Mobile into existing mobiles List
	 * @param mobile
	 * @return
	 */
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public  Response saveMobile(@RequestBody @Valid SaveMobileRequest saveMobile) {		
		MobileDTO mobileDTO=  mobileService.save(saveMobile); 
		return Response.builder().response(mobileDTO).build();
	}
	
	@GetMapping
	public Response getAllMobiles(){
		List<MobileDTO> mobilesDtos =  mobileService.getMoblies();
		
		return Response.builder().response(mobilesDtos).build();
	}
	
	@DeleteMapping("{mobile-id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public Response deleteMobileById(@PathVariable("mobile-id") int mobileId) {
		mobileService.deleteMobile(mobileId);
		return Response.builder().response(true).build();
	}
	
	@PutMapping
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public  Response updateMobile(@RequestBody @Valid UpdateMobileRequest mobile) {
		 mobileService.update(mobile); 
		 return Response.builder().response(true).build();
	}


}
