package com.spring.boot.msk.mobileclient.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class UserService  {
	
	//TO-DO Load User Details From Mongo DB
	
	public Mono<UserDetails>  loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return Mono.just(
				User
				.builder()
				.username("user")
				.password("$2y$12$sppmYRchxJZc.qtmy/bQre6XtEK5/JkgunHIATp26sqRmRAUdr3Uu")
				.roles("ADMIN")
				.build()
				);
	}

	
	
	
}
