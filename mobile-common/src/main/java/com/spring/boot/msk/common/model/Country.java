package com.spring.boot.msk.common.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Country {
	
	
	@NotBlank(message = "{NotBlank.Country.code}")
    private String code;
	
	@NotBlank(message = "{NotBlank.Country.name}")
	private String name;
	
	@NotBlank(message = "{NotBlank.Country.description}")
	private String description;
	
	@NotBlank(message = "{NotBlank.Country.continent}")
	private String continent;
	
	@NotBlank(message = "{NotBlank.Country.region}")
	private String region;
	
	@Min(value = 1, message = "{Min.Country.population}")
	private long population;

	
	
}
