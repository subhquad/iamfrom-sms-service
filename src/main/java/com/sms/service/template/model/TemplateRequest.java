package com.sms.service.template.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.sms.service.enums.TemplateTypeEnum;

public class TemplateRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Please provide a valid template name")
	private String templateName;

	@NotNull(message = "Please provide a template type")
	private TemplateTypeEnum templateType;

	@NotEmpty(message = "Please provide the template variables")
	List<TemplateVariable> requestParams;

	public String getTemplateName() {
		return templateName;
	}

	public TemplateRequest setTemplateName(String templateName) {
		this.templateName = templateName;
		return this;
	}

	public TemplateTypeEnum getTemplateType() {
		return templateType;
	}

	public TemplateRequest setTemplateType(TemplateTypeEnum templateType) {
		this.templateType = templateType;
		return this;
	}

	public List<TemplateVariable> getRequestParams() {
		if (requestParams == null) {
			requestParams = new ArrayList<>();
		}
		return requestParams;
	}

	public void setRequestParams(List<TemplateVariable> requestParams) {
		this.requestParams = requestParams;
	}
}
