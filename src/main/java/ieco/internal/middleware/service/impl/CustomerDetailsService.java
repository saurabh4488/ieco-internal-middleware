package ieco.internal.middleware.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import ieco.internal.middleware.domain.request.CustomerDetailsRequest;
import ieco.internal.middleware.domain.response.OtpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
@Slf4j
public class CustomerDetailsService {

	@Autowired
	private RestTemplate restTemplate;
	
	public boolean getCustomerdetails() throws JsonMappingException, JsonProcessingException {
		CustomerDetailsRequest customerDetails = new CustomerDetailsRequest();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("ContentType", "application/json");
		httpHeaders.add("Authorization", "Bearer c6d92f6b-49db-39bc-bee9-a5ab6509c634");
ResponseEntity<OtpResponse> otpResponse = restTemplate.postForEntity("http://iecocms.eastus.cloudapp.azure.com/api/ieco-customer/v1/customers/customerDetails", new HttpEntity<CustomerDetailsRequest>(customerDetails, httpHeaders), OtpResponse.class);
		HttpStatus statusCode = otpResponse.getStatusCode();
		log.info("otpResponse statusCode -{}", statusCode);
		String status = otpResponse.getBody().getStatus();
		log.info("otpResponse body status - {}", status);

		  if(otpResponse.getStatusCode().is2xxSuccessful()) {
			  if(otpResponse.getBody().getStatus().equalsIgnoreCase("200 OK")) {
				  return true;
			  }
			  else if(otpResponse.getBody().getStatus().equalsIgnoreCase("500 INTERNAL_SERVER_ERROR")) {
				  OtpResponse body = otpResponse.getBody();
				  log.info("otpResponse body - {}", body);
				  return false;
			  }
		  }
		  return false;
		 
		
		
	}
	
	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		boolean customerdetails = new CustomerDetailsService().getCustomerdetails();
		log.info("customerdetails - {}", customerdetails);
	}
}
