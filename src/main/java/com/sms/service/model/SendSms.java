package com.sms.service.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

public class SendSms implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Please provide a recipient")
	private String recipient;

	@NotEmpty(message = "Please provide a template id")
	private String templateId;

	List<RequestParam> parameters;

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public List<RequestParam> getParameters() {
		return parameters;
	}

	public void setParameters(List<RequestParam> parameters) {
		this.parameters = parameters;
	}

}
