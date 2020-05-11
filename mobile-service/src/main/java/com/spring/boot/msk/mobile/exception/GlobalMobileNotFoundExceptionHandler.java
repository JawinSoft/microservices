package com.spring.boot.msk.mobile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
	
	
	/*@ExceptionHandler(Throwable.class)
	public ResponseEntity<ErrorDetails> handleMobileNotFoundException(Throwable ex){
	    
	  return ResponseEntity
			.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(
					new ErrorDetails(10009,ex.getMessage() )
				);    
	}*/


}
