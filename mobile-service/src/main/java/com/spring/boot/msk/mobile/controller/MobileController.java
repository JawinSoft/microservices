package com.spring.boot.msk.mobile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.msk.mobile.model.Mobile;
import com.spring.boot.msk.mobile.service.MobileService;



@RestController
public class MobileController {
	
	@Autowired
	private MobileService mobileService;
	
	
	@GetMapping("mobiles/{mobileId}")//{mobileId} --> Path Variable
	public Mobile getMobileById(@PathVariable("mobileId") int id) {
		return mobileService.getMobileById(id);
	}
	
	/**
	 * Method to add a new Mobile into existing mobiles List
	 * @param mobile
	 * @return
	 */
	@PostMapping("mobiles")
	@ResponseStatus(code = HttpStatus.CREATED)
	public  Mobile saveMobile(@RequestBody Mobile mobile) {
		
		return mobileService.save(mobile); 
	}
	
	@GetMapping("mobiles")
	public List<Mobile> getAllMobiles(@RequestParam(name = "brand", required = false) String name){
		return mobileService.getMoblies();
	}
	
	@DeleteMapping("mobiles/{mobile-id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteMobileById(@PathVariable("mobile-id") int mobileId) {
		mobileService.deleteMobile(mobileId);
	}
	
	

	@PutMapping("mobiles")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public  void updateMobile(@RequestBody Mobile mobile) {
		 mobileService.update(mobile); 
	}
	
	
	/*@ExceptionHandler(MobileNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleMobileNotFoundException(MobileNotFoundException ex){
	    
	  return ResponseEntity
			.status(HttpStatus.NOT_FOUND)
			.body(
					new ErrorDetails(10001, "Mobile Not Found")
				);    
	}*/


}
