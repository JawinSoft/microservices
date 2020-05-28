package com.spring.boot.msk.accessories.exception;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

import com.spring.boot.msk.common.dto.ErrorDetails;
import com.spring.boot.msk.common.dto.Response;

import reactor.core.publisher.Mono;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	
	@ExceptionHandler(MobileAccessoryNotFoundException.class)
	public ResponseEntity<Mono<Response>> handleMobileAccessoryNotFoundException(MobileAccessoryNotFoundException ex){
		ErrorDetails error = ErrorDetails.builder().code(10002).message(ex.getMessage()).build(); 
		
		Response response = Response.builder().errors(Arrays.asList(error)).build();
		
		return ResponseEntity.badRequest().body( Mono.just(response));
		
	
	}
	
	
	@ExceptionHandler(WebExchangeBindException.class)
	public ResponseEntity<Mono<Response>> handleWebExchangeBindException(WebExchangeBindException ex){
		
		List<ErrorDetails> errors = ex.getAllErrors().stream().map(error -> 
			 ErrorDetails.builder().code(10002).message(error.getDefaultMessage()).build())
		.collect(Collectors.toList());
		
		return ResponseEntity
				.badRequest()
				.body(
						Mono.just(
								Response.builder().errors(errors).build() 
								)//Mono
					);//Response Entity
	}
	
	

}
