package com.spring.boot.msk.accessories.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MobileAccessory {

	private String id;
	
	private String name;
	
	private String description;
	
	private String mobileType;
	
	
}
