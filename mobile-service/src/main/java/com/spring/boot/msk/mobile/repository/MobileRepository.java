package com.spring.boot.msk.mobile.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.msk.mobile.entity.Mobile;

@Repository
public interface MobileRepository extends CrudRepository<Mobile, Integer>{

}
