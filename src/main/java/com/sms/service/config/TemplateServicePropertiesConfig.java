package com.sms.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * This class will be responsible for loading the configurations from
 * application.yml All the configurations under template.service will be mapped to this
 * class.
 * 
 *
 */
@Component
@ConfigurationProperties(prefix = "template.service")
public class TemplateServicePropertiesConfig {

	private String url;
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
}
