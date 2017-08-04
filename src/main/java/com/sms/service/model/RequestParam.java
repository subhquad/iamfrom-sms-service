package com.sms.service.model;

import java.io.Serializable;

public class RequestParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String paramName;
	private String paramValue;
	
	public String getParamName() {
		return paramName;
	}
	
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	
	public String getParamValue() {
		return paramValue;
	}
	
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
}
