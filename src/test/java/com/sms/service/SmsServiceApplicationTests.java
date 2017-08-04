package com.sms.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sms.service.model.SendSms;
import com.sms.service.test.validator.RequestValidator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmsServiceApplicationTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(SmsServiceApplicationTests.class);

	@Autowired
	private RequestValidator requestValidator;

	private String validRecipient = "919958337759";
	private String validTemplate = "order_placed";

	@Test
	public void contextLoads() {
		LOGGER.info("Context loaded successfully");
	}

	@Test
	public void testEmptyTemplateName() {
		try {
			SendSms sms = new SendSms();
			sms.setRecipient(validRecipient);
			requestValidator.validate(sms);
		} catch (Exception ex) {
			LOGGER.error("testEmtpyTemplateName {}", ex);
			assertNotNull(ex);
		}
	}

	@Test
	public void testEmptyRecipient() {
		try {
			SendSms sms = new SendSms();
			sms.setTemplateId(validTemplate);
			requestValidator.validate(sms);
		} catch (Exception ex) {
			LOGGER.error("testEmtpyTemplateName {}", ex);
			assertNotNull(ex);
		}
	}

}
