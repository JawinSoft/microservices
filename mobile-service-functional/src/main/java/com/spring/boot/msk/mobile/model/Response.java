package com.spring.boot.msk.mobile.model;



public class Response<T> {
	
	
	private T responseData;

	public T getResponseData() {
		return responseData;
	}

	public void setResponseData(T responseData) {
		this.responseData = responseData;
	}
	
	

}
