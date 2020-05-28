package com.spring.boot.msk.mobile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.spring.boot.msk.common.dto.ErrorDetails;

@ControllerAdvice
public class GlobalMobileNotFoundExceptionHandler {
	
	@ExceptionHandler(MobileNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleMobileNotFoundException(MobileNotFoundException ex){
	    
	  return ResponseEntity
			.status(HttpStatus.NOT_FOUND)
			.body(
					new ErrorDetails(10001, "Mobile Not Found")
				);    
	}
	
	
	


}
