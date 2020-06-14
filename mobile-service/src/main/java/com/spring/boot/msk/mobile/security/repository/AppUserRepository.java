package com.spring.boot.msk.mobile.security.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.spring.boot.msk.mobile.security.model.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, Integer>{

	Optional<AppUser> findByUserName(String userName);
	
}
