package com.spring.boot.msk.accessories.dto;



import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SaveMobileAccessoryRequest {

	@NotBlank(message="{NotBlank.saveMobileAccessoryRequest.name}")
    private String name;
	
	@NotBlank(message="{NotBlank.saveMobileAccessoryRequest.description}")
	private String description;
	
	private String mobileType;
		
	
}
