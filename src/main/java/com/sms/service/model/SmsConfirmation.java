package com.sms.service.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SmsConfirmation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String status;
	private String errorCode;
	private String errorMessage;

	public String getStatus() {
		return status;
	}

	public SmsConfirmation setStatus(String status) {
		this.status = status;
		return this;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public SmsConfirmation setErrorCode(String errorCode) {
		this.errorCode = errorCode;
		return this;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public SmsConfirmation setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}

}
