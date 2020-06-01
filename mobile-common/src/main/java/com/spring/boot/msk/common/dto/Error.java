package com.spring.boot.msk.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Error {

	
	private int code;
	
	private String message;
	
	 


}
