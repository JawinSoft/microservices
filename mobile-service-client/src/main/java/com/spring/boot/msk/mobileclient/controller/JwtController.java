package com.spring.boot.msk.mobileclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.msk.mobileclient.dto.CreateTokenRequest;
import com.spring.boot.msk.mobileclient.service.JwtService;
import com.spring.boot.msk.mobileclient.service.UserService;

import reactor.core.publisher.Mono;

@RestController
public class JwtController {

	@Autowired
	UserService userService;

	@Autowired
	private JwtService jwtService;	 
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@PostMapping("/create-token")
	public Mono<String> createToken(@RequestBody CreateTokenRequest request) {
		
		Mono<UserDetails> userDetails = userService.loadUserByUsername(request.getUserName());
		
		return userDetails.flatMap(user -> {
			if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {

				return Mono.just(
						jwtService.generateToken(request.getUserName(), user)
						);
		}

		return Mono.error(new RuntimeException("Invalid User to Login"));

		});
	}
}
