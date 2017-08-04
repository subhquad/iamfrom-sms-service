package com.sms.service.business.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.sms.service.business.SmsService;
import com.sms.service.config.AwsPropertiesConfig;
import com.sms.service.enums.StatusEnum;
import com.sms.service.enums.TemplateTypeEnum;
import com.sms.service.exception.BusinessException;
import com.sms.service.model.SendSms;
import com.sms.service.model.SmsConfirmation;
import com.sms.service.template.model.TemplateResponse;

/**
 * This class will be responsible for initializing the AWS SMS client, SMS
 * attributes and sending the SMS.
 * 
 */
@Component
public class AwsSmsService extends AbstractService implements SmsService {

	private final static Logger LOGGER = LoggerFactory.getLogger(AwsSmsService.class);

	@Autowired
	private AwsPropertiesConfig awsProperties;

	/**
	 * This function will be responsible for sending the sms through AWS
	 * gateway.
	 */
	@Override
	public SmsConfirmation sendSms(SendSms smsRequest) throws BusinessException {
		try {
			LOGGER.info("Sms request for recipient {} template {} ", smsRequest.getRecipient(),
					smsRequest.getTemplateId());
			String request = createTemplateServiceRequest(smsRequest.getTemplateId(), TemplateTypeEnum.sms,
					smsRequest.getParameters());
			String templateResponse = serviceUtils.post(templateServiceProperties.getUrl(), request);
			TemplateResponse templateResponseObj = conversionUtils.convertStringToObj(templateResponse,
					TemplateResponse.class);
			if (templateResponseObj != null) {
				if (templateResponseObj.getErrorCode() != null) {
					throw new BusinessException(templateResponseObj.getErrorCode(),
							templateResponseObj.getErrorMessage(), null);
				} else {
					String messageId = sendSMS(getSMSClient(), templateResponseObj.getTemplate(),
							smsRequest.getRecipient(), prepareSMSAttributes());
					LOGGER.info("Message sent with message Id {} for recipient {} templateId {}", messageId,
							smsRequest.getRecipient(), smsRequest.getTemplateId());
				}
			}
			SmsConfirmation confirmation = new SmsConfirmation();
			confirmation.setStatus(StatusEnum.Success.name());
			return confirmation;

		} catch (BusinessException ex) {
			LOGGER.error("An business exception occured while sending the sms {}", ex);
			throw ex;
		} catch (Exception ex) {
			LOGGER.error("An exception occured while sending the sms {}", ex);
			throw new BusinessException("GENERROR", "An error occured", ex);
		}

	}

	/**
	 * This function will be responsible for initializing the SMS attributes
	 * 
	 * @return :: Map of sms attributes
	 */
	public Map<String, MessageAttributeValue> prepareSMSAttributes() {
		Map<String, MessageAttributeValue> smsAttributes = new HashMap<>();
		smsAttributes.put("AWS.SNS.SMS.SenderID",
				new MessageAttributeValue().withStringValue(awsProperties.getSenderid()).withDataType("String"));
		smsAttributes.put("AWS.SNS.SMS.SMSType",
				new MessageAttributeValue().withStringValue(awsProperties.getSmstype()).withDataType("String"));

		return smsAttributes;
	}

	/**
	 * This function will be responsible for sending the SMS.
	 * 
	 * @param snsClient
	 * @param message
	 * @param recipient
	 * @param smsAttributes
	 * @return
	 */
	public String sendSMS(AmazonSNSClient snsClient, String message, String recipient,
			Map<String, MessageAttributeValue> smsAttributes) {
		LOGGER.info("Sending SMS with message {} recipient {}", message, recipient);
		PublishResult result = snsClient.publish(new PublishRequest().withMessage(message).withPhoneNumber(recipient)
				.withMessageAttributes(smsAttributes));
		return result.getMessageId();
	}

	/**
	 * This function will be responsible for initializing the SMS client.
	 * 
	 * @return
	 */
	public AmazonSNSClient getSMSClient() {
		return new AmazonSNSClient(new BasicAWSCredentials(awsProperties.getAccesskey(), awsProperties.getSecretkey()));
	}

}
