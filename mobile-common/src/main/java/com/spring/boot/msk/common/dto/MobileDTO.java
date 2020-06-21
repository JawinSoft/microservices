package com.spring.boot.msk.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MobileDTO {

	private int id;
	
	private String name;

	private int price;

	private String status;

	private String lob;

	private String countryCode;
	
	private String publictionDate;
	
	private String serverPort;
}
