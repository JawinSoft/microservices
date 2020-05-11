package com.spring.boot.msk.mobile.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import com.spring.boot.msk.mobile.model.Mobile;
import com.spring.boot.msk.mobile.service.MobileService;

@Component
public class MobileHandler {
	
	@Autowired
	private MobileService mobileService;
	
	
	public ServerResponse getAllMobiles(ServerRequest serverRequest) {
		
		List<Mobile> allMobiles = mobileService.getMoblies();
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(allMobiles);
		
	}
	
	


}
