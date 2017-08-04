package com.sms.service.utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ServiceUtils {

	private final static Logger LOGGER = LoggerFactory.getLogger(ServiceUtils.class);

	/**
	 * This function will be responsible for making the post request
	 * 
	 * @param url
	 *            :: url for making the post request
	 * @param reqBody
	 *            :: body to be sent in the post request
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String post(String url, String reqBody) throws ClientProtocolException, IOException {
		LOGGER.info("Url {} request Body {}", url, reqBody);
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);

		StringEntity entity = new StringEntity(reqBody);
		httpPost.setEntity(entity);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");

		CloseableHttpResponse response = client.execute(httpPost);
		String responseStr = null;
		if (response != null) {
			HttpEntity httpentity = response.getEntity();
			responseStr = EntityUtils.toString(httpentity);

		}
		client.close();
		LOGGER.info("Response {}", responseStr);
		return responseStr;
	}
}
