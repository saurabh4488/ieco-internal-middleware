package ieco.internal.middleware.service.impl;

import ieco.internal.middleware.domain.response.ZohoTokenGenResponse;
import ieco.internal.middleware.util.RestUtility;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ZohoOAuth {
	org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Value("${ZohotokengenerationURL}")
	private String oAuthUrl;
	

	@Autowired
	private RestUtility restUtility;
	public String generateAccessToken() {
		ResponseEntity<ZohoTokenGenResponse> ZohoTokenGenResponse=restUtility.post(oAuthUrl, null, null, ZohoTokenGenResponse.class);
		
		log.info("zoho oauth token response {}",ZohoTokenGenResponse.getBody().getAccess_token());
		return ZohoTokenGenResponse.getBody().getAccess_token();

	}

}
