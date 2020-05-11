package com.spring.boot.msk.country.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.msk.country.model.Country;
import com.spring.boot.msk.country.repository.CountryRepository;

@RestController
public class CountryController {
	
	@Autowired
	private CountryRepository countryRepository;


	@GetMapping("getAllCountries")
	public List<Country> getAllCouuntries(){
		return countryRepository.getAllCountries();
	}
	
	
}
