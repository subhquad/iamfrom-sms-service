package com.sms.service.test.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestValidator {

	public static Logger logger = LoggerFactory.getLogger(RequestValidator.class);

	@Autowired
	private Validator validator;

	public <T> void validate(T t) {
		Class[] groups = { Default.class };
		validation(t, groups);
	}

	private <T> void validation(T t, Class[] groups) throws ConstraintViolationException {
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(t, groups);
		if (constraintViolations.size() > 0) {

			StringBuilder errorMessage = new StringBuilder();
			for (ConstraintViolation<T> violation : constraintViolations) {
				if (errorMessage.length() > 0)
					errorMessage.append(", ");
				errorMessage.append(violation.getMessage());
			}
			throw new ConstraintViolationException(errorMessage.toString(), null);
		}
	}
}
