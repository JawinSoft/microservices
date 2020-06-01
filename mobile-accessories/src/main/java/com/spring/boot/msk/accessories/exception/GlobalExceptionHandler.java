package com.spring.boot.msk.accessories.exception;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

import com.spring.boot.msk.common.dto.Error;
import com.spring.boot.msk.common.dto.Errors;
import com.spring.boot.msk.common.dto.Response;


import reactor.core.publisher.Mono;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MobileAccessoryNotFoundException.class)
	public ResponseEntity<Mono<Response<Errors>>> handleMobileAccessoryNotFoundException(MobileAccessoryNotFoundException ex){
		Error error = Error.builder().code(10002).message(ex.getMessage()).build(); 
		
		Errors errors = Errors.builder().errors(Arrays.asList(error)).build();
		
		return ResponseEntity.badRequest().body( Mono.just(Response.<Errors>builder().response(errors).build()));
		
	
	}

	@ExceptionHandler(WebExchangeBindException.class)
	public ResponseEntity<Mono<Response<Errors>>> handleWebExchangeBindException(WebExchangeBindException ex) {
		Response<Errors> response = buildErrorRespone(ex.getBindingResult());

		return ResponseEntity.badRequest().body(Mono.just(response)// Mono
		);// Response Entity
	}

	private Response<Errors> buildErrorRespone(BindingResult bindingResult) {
		List<Error> errorDetails = bindingResult.getAllErrors().stream()
				.map(error -> Error.builder().code(1001).message(error.getDefaultMessage()).build())
				.collect(Collectors.toList());

		Errors errors = Errors.builder().errors(errorDetails).build();

		return Response.<Errors>builder().response(errors).build();

	}

}
