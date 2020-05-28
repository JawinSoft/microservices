package com.spring.boot.msk.common.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.spring.boot.msk.common.dto.ErrorDetails;
import com.spring.boot.msk.common.dto.Response;

@ControllerAdvice
public class CommonGlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
	    
		List<ErrorDetails> errorDetails = ex.getBindingResult().getAllErrors().stream().map(error -> {
			ErrorDetails erorDetail = new ErrorDetails(1001, error.getDefaultMessage());
			return erorDetail;
		}).collect(Collectors.toList());
		
	  return ResponseEntity
			.status(HttpStatus.BAD_REQUEST)
			.body(
					Response.builder().errors(errorDetails).build()
				);    
	}
	
	
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<Response> handleThrowable(Throwable ex){
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		
		String stackTrace = sw.toString();
	    
	  return ResponseEntity
			.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(
					Response.builder().errors(Arrays.asList(new ErrorDetails(10009,ex.getMessage(), stackTrace))).build()
				);    
	}
	
}
