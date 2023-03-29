package ieco.internal.middleware.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import ieco.internal.middleware.domain.request.CustomerDetailsRequest;
import ieco.internal.middleware.domain.response.OtpResponse;
import ieco.internal.middleware.feignclient.CustomerDetailsClient;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomerDetailsService {

	@RestClient
	private CustomerDetailsClient customerDetailsClient;

	public String authorization="Bearer c6d92f6b-49db-39bc-bee9-a5ab6509c634";

	public String contentType="application/json";

	public boolean getCustomerdetails() throws JsonMappingException, JsonProcessingException {
		CustomerDetailsRequest customerDetails = new CustomerDetailsRequest();

		ResponseEntity<OtpResponse> otpResponse=customerDetailsClient.getOtpResponse(customerDetails,contentType,authorization);
		
		  log.info(String.valueOf(otpResponse.getStatusCode()));
		  log.info(otpResponse.getBody().getStatus());
		  if(otpResponse.getStatusCode().is2xxSuccessful()) {
			  if(otpResponse.getBody().getStatus().equalsIgnoreCase("200 OK")) {
				  return true;
			  }
			  else if(otpResponse.getBody().getStatus().equalsIgnoreCase("500 INTERNAL_SERVER_ERROR")) {
				  log.info(String.valueOf(otpResponse.getBody()));
				  return false;
			  }
		  }
		  return false;
		 
		
		
	}
	
	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		log.info("Customer Details"+new CustomerDetailsService().getCustomerdetails());
	}
}
