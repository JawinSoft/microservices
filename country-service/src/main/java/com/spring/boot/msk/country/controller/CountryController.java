package com.spring.boot.msk.country.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.msk.common.dto.Response;
import com.spring.boot.msk.common.model.Country;
import com.spring.boot.msk.country.service.CountryService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("country")
public class CountryController {
	
	@Autowired
	private CountryService countryService;
	
	@GetMapping("{country-code}")
	public Response<Country> getCountryByCode(@PathVariable("country-code") String countryCode) {
		Country country =  countryService.getCountryByCode(countryCode);		
		return Response.<Country>builder().response(country).build();
	}
	
	@GetMapping("region/{region}")
	public Response<Country> getCountryByRegion(@PathVariable("region") String region) {
		Country country =  countryService.getCountryByRegion(region);
		return Response.<Country>builder().response(country).build();
	}
	
	
	@GetMapping
	public Response<List<Country>> getAllCountries() {
		List<Country> countries =  countryService.getAllCountries();		
		return Response.<List<Country>>builder().response(countries).build();
	}
	
	@PostMapping
	public Response<Country> updateMobile(@RequestBody @Valid Country country){
		Country saveCountry = countryService.updateMobile(country);
		return Response.<Country>builder().response(saveCountry).build();
	}
	
	
	@DeleteMapping("{country-code}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public Response<Void> deleteCountry(@PathVariable("country-code") String countryCode) {
		
		countryService.deleteCountry(countryCode);
		
		return Response.<Void>builder().build();
	}
	
	
	
}
