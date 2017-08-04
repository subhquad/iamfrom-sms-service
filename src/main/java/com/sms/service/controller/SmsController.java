package com.sms.service.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sms.service.business.SmsService;
import com.sms.service.exception.BusinessException;
import com.sms.service.model.SendSms;
import com.sms.service.model.SmsConfirmation;

/**
 * Rest endpoint for sending the SMS
 * 
 */
@Controller
@RequestMapping(value = "/iamfrom/v1")
public class SmsController {

	private final static Logger LOGGER = LoggerFactory.getLogger(SmsController.class);

	@Autowired
	private SmsService smsService;

	/**
	 * This api will be responsible for listing the template and sending the
	 * sms.
	 * 
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/sendSms", method = RequestMethod.POST)
	public @ResponseBody SmsConfirmation sendSms(@RequestBody @Valid SendSms smsRequest) throws BusinessException {
		LOGGER.debug("Inside sendSms");
		SmsConfirmation confirmation = smsService.sendSms(smsRequest);
		return confirmation;
	}
}
