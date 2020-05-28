package com.spring.boot.msk.mobile.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MobileDTO {

	private int id;
	
	private String name;

	private int price;

	private String status;

	private String lob;

	private String countryCode;
	
	private String publictionDate;
}
