package com.sms.service.exception.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sms.service.enums.StatusEnum;
import com.sms.service.exception.BusinessException;
import com.sms.service.model.SmsConfirmation;

@ControllerAdvice
public class SmsServiceExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(SmsServiceExceptionHandler.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public SmsConfirmation validationError(MethodArgumentNotValidException ex) {
		StringBuilder builder = new StringBuilder();
		BindingResult result = ex.getBindingResult();
		final List<FieldError> fieldErrors = result.getFieldErrors();
		fieldErrors.forEach(fieldError -> {
			builder.append(fieldError.getDefaultMessage());
		});
		return getSmsConfirmation("GENERRORCODE", builder.toString());
	}

	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public SmsConfirmation validationError(BusinessException ex) {
		return getSmsConfirmation(ex.getErrorCode(), ex.getErrorMessage());
	}

	private SmsConfirmation getSmsConfirmation(String errorCode, String errorMessage) {
		SmsConfirmation confirmation = new SmsConfirmation();
		confirmation.setErrorCode(errorCode).setErrorMessage(errorMessage).setStatus(StatusEnum.Failure.name());
		return confirmation;
	}
}