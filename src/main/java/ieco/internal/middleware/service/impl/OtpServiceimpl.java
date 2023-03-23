package ieco.internal.middleware.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ieco.internal.middleware.domain.request.MiddlewareRequest;
import ieco.internal.middleware.domain.request.OtpRequest;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OtpServiceimpl {

	private org.slf4j.Logger log = LoggerFactory.getLogger(OtpServiceimpl.class);
	
	@Autowired
	private RestTemplate restTemplate;

	public boolean verifyOtp(MiddlewareRequest middlewarerequest) throws JsonMappingException, JsonProcessingException {

		OtpRequest otpReq = new OtpRequest();
		otpReq.setCommunicationTypeId(1);
		otpReq.setCustomerId(middlewarerequest.getTempCustomerId());
		otpReq.setEmailId(middlewarerequest.getEmail());
		otpReq.setMobileNumber(middlewarerequest.getMobileNumber());
		otpReq.setOtpNumber(middlewarerequest.getOtp());
		otpReq.setSessionLogId(middlewarerequest.getSessionId());
		otpReq.setTransactionId(middlewarerequest.getTransactionId());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("ContentType", "application/json");

		ResponseEntity<String> otpResponse = restTemplate.postForEntity("",
				new HttpEntity<OtpRequest>(otpReq, httpHeaders), String.class);
		log.info("OTP Validation response from PwC : {}",otpResponse.getBody());
		JsonNode actualObj = new ObjectMapper().readTree(otpResponse.getBody());
		if (actualObj.get("status").asText().equalsIgnoreCase("200 ok")) {
			log.info("Successfully verify the otp");
			return true;
		} else {
log.info("OTP verification failed");

return false;
		}
		
	}

}