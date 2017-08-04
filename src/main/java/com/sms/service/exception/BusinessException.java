package com.sms.service.exception;

public class BusinessException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BusinessException(String errorCode, String errorMessage, Throwable cause) {
		super(errorCode, errorMessage, cause);
	}

}
