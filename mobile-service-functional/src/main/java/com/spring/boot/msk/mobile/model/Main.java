package com.spring.boot.msk.mobile.model;

public class Main {
	
	public static void main(String[] args) {
		//-----------------------------------------
		Response<String> response = new Response<String>();
		
		response.setResponseData("SunilManaka");
		
		//response.setResponseData(100);
		
		/////----
		String str = response.getResponseData();
		
		System.out.println(str);
		
		
	
		
	}

}
