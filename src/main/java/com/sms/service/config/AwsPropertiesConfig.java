package com.sms.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * This class will be responsible for loading the configurations from
 * application.yml All the configurations under aws will be mapped to this
 * class.
 * 
 *
 */
@Component
@ConfigurationProperties(prefix = "aws")
@EnableConfigurationProperties

public class AwsPropertiesConfig {

	private String accesskey;
	private String secretkey;
	private String senderid;
	private String smstype;

	public String getAccesskey() {
		return accesskey;
	}

	public void setAccesskey(String accesskey) {
		this.accesskey = accesskey;
	}

	public String getSecretkey() {
		return secretkey;
	}

	public void setSecretkey(String secretkey) {
		this.secretkey = secretkey;
	}

	public String getSenderid() {
		return senderid;
	}

	public void setSenderid(String senderid) {
		this.senderid = senderid;
	}

	public String getSmstype() {
		return smstype;
	}

	public void setSmstype(String smstype) {
		this.smstype = smstype;
	}

}
