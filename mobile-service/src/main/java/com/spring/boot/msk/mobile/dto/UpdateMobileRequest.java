package com.spring.boot.msk.mobile.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UpdateMobileRequest {
	
	//@NotBlank(message="NotBlank.UpdateMobileRequest.id")
	@Min(value = 1, message = "{NotBlank.UpdateMobileRequest.id}")
	private int id;
	
	@NotBlank(message="{NotBlank.UpdateMobileRequest.name}")
	private String name;

	@Min(value = 1, message = "{NotBlank.UpdateMobileRequest.price}")
	private int price;

	@NotBlank(message="{NotBlank.UpdateMobileRequest.status}")
	private String status;

	@NotBlank(message="{NotBlank.UpdateMobileRequest.lob}")
	private String lob;

	@NotBlank(message="{NotBlank.UpdateMobileRequest.countryCode}")
	private String countryCode;
	
	
}
