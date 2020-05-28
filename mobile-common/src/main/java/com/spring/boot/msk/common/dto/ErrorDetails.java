package com.spring.boot.msk.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ErrorDetails {

	public ErrorDetails(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	
	public ErrorDetails(int code, String message, String stackTrace) {
		this(code, message);
		this.stackTrace = stackTrace;
	}
	
	private int code;
	
	private String message;
	
	@JsonInclude(Include.NON_NULL)
	private String stackTrace; 


}
