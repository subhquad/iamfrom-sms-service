package com.sms.service.utils;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component
public class ConversionUtils {

	public String convertObjToString(Object obj) {
		Gson gson = new Gson();
		String request = gson.toJson(obj);
		return request;
	}

	public <T> T convertStringToObj(String response, Class<T> clazz) {
		Gson gson = new Gson();
		T responseObj = gson.fromJson(response, clazz);
		return responseObj;
	}
}
