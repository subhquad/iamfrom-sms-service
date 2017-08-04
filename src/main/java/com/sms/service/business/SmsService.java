package com.sms.service.business;

import com.sms.service.exception.BusinessException;
import com.sms.service.model.SendSms;
import com.sms.service.model.SmsConfirmation;

public interface SmsService {

	public SmsConfirmation sendSms(SendSms smsRequest) throws BusinessException;
}
