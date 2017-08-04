package com.sms.service.business.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sms.service.config.TemplateServicePropertiesConfig;
import com.sms.service.enums.TemplateTypeEnum;
import com.sms.service.model.RequestParam;
import com.sms.service.template.model.TemplateRequest;
import com.sms.service.template.model.TemplateVariable;
import com.sms.service.utils.ConversionUtils;
import com.sms.service.utils.ServiceUtils;

/**
 * This abstract service will be responsible for performing the operations which
 * are common across all the SMS gateways
 * 
 */
public abstract class AbstractService {

	private final static Logger LOGGER = LoggerFactory.getLogger(AbstractService.class);

	@Autowired
	protected TemplateServicePropertiesConfig templateServiceProperties;

	@Autowired
	protected ServiceUtils serviceUtils;

	@Autowired
	protected ConversionUtils conversionUtils;

	/**
	 * This function will be responsible for creating the template request
	 * object required fo template-service
	 * 
	 * @param templateId
	 *            :: template to be fetched
	 * @param templateType
	 *            :: SMS or email
	 * @param parameters
	 *            :: parameters to be replaced in the template
	 * @return
	 */
	protected String createTemplateServiceRequest(String templateId, TemplateTypeEnum templateType,
			List<RequestParam> parameters) {
		LOGGER.debug("Creating template request for template {} templateType {}", templateId, templateType);
		TemplateRequest templateRequest = new TemplateRequest();
		templateRequest.setTemplateName(templateId).setTemplateType(templateType);

		if (parameters != null && !parameters.isEmpty()) {
			LOGGER.debug("Template request parameters not empty {}", parameters.size());
			parameters.forEach(requestParam -> templateRequest.getRequestParams().add(new TemplateVariable()
					.setParamName(requestParam.getParamName()).setParamValue(requestParam.getParamValue())));
		}

		return conversionUtils.convertObjToString(templateRequest);
	}

}
