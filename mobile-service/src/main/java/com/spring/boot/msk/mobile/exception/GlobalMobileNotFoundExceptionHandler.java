package com.spring.boot.msk.mobile.exception;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.spring.boot.msk.common.dto.Error;
import com.spring.boot.msk.common.dto.Errors;
import com.spring.boot.msk.common.dto.Response;

@ControllerAdvice
public class GlobalMobileNotFoundExceptionHandler {

	@ExceptionHandler(MobileNotFoundException.class)
	public ResponseEntity<Response<Errors>> handleMobileNotFoundException(MobileNotFoundException ex) {

		Errors errors = Errors.builder().errors(Arrays.asList(Error.builder().code(10001).message(ex.getMessage()).build())).build();
		
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(Response.<Errors>builder().response(errors).build());
	}
	
	
	

}
