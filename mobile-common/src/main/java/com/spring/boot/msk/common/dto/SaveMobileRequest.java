package com.spring.boot.msk.common.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveMobileRequest {
	
	@NotBlank(message = "{NotBlank.saveMobileRequest.name}")
	private String name;

	@Min(value = 1, message="{NotBlank.saveMobileRequest.price}")
	private int price;

	@NotBlank(message = "{NotBlank.saveMobileRequest.status}")
	private String status;

	@NotBlank(message = "{NotBlank.saveMobileRequest.lob}")
	private String lob;

	@NotBlank(message = "{NotBlank.saveMobileRequest.countryCode}")
	private String countryCode;	
	

}
