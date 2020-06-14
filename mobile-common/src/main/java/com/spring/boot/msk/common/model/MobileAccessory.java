package com.spring.boot.msk.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MobileAccessory {

	private String id;
	
	private String name;
	
	private String description;
	
	private String mobileType;
	
	
}
