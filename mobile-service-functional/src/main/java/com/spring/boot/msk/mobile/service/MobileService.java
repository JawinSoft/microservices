package com.spring.boot.msk.mobile.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.spring.boot.msk.mobile.model.Mobile;

@Service
public class MobileService {

	private List<Mobile> moblies = new ArrayList<>();

	public Mobile getMobileById(int mobileId) {

		Optional<Mobile> mobileFound = moblies.stream().filter(mobile -> mobile.getId() == mobileId).findFirst();

		try {
			TimeUnit.SECONDS.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		if (!mobileFound.isPresent()) {
			throw new RuntimeException("Mobile Not Found..!"+mobileId);
		}

		return mobileFound.get();
	}

	@PostConstruct
	public void initMobiles() {
		moblies.add(new Mobile(1, "Samsung", 10000));
		moblies.add(new Mobile(2, "MI", 12000));
		moblies.add(new Mobile(3, "Honor 20", 15000));
		moblies.add(new Mobile(4, "Redmi Note 8 Pro", 11500));
		moblies.add(new Mobile(5, "Vivo S1 Pro", 12000));
		moblies.add(new Mobile(6, "OPPO A52020", 14000));
		moblies.add(new Mobile(7, "Nokia 6.2", 16000));
	}

	public List<Mobile> getMoblies() {
		return moblies;
	}

	public void setMoblies(List<Mobile> moblies) {
		this.moblies = moblies;
	}

	public Mobile save(Mobile mobile) {

		Optional<Mobile> mobileFound = moblies.stream().filter(dbMobile -> dbMobile.getId() == mobile.getId())
				.findFirst();
		if (!mobileFound.isPresent()) {
			moblies.add(mobile);
		}
		return mobile;
	}

	public void deleteMobile(int mobileId) {
		// TO-DO -- Delete mobile number by ID
	}

	public Mobile update(Mobile mobile) {
		// TODO Auto-generated method stub
		return null;
	}

}
