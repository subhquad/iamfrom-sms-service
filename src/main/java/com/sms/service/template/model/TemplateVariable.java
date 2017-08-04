package com.sms.service.template.model;

import java.io.Serializable;

public class TemplateVariable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String paramName;
	private String paramValue;

	public String getParamName() {
		return paramName;
	}

	public TemplateVariable setParamName(String paramName) {
		this.paramName = paramName;
		return this;
	}

	public String getParamValue() {
		return paramValue;
	}

	public TemplateVariable setParamValue(String paramValue) {
		this.paramValue = paramValue;
		return this;
	}
}
