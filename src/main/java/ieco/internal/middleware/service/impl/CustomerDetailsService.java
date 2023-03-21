package ieco.internal.middleware.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import ieco.internal.middleware.domain.request.CustomerDetailsRequest;
import ieco.internal.middleware.domain.response.OtpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class CustomerDetailsService {

	@Autowired
	private RestTemplate restTemplate;
	
	public boolean getCustomerdetails() throws JsonMappingException, JsonProcessingException {
		CustomerDetailsRequest customerDetails = new CustomerDetailsRequest();
		
		//customerDetails.setCustomerId(100010);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("ContentType", "application/json");
		httpHeaders.add("Authorization", "Bearer c6d92f6b-49db-39bc-bee9-a5ab6509c634");
ResponseEntity<OtpResponse> otpResponse = restTemplate.postForEntity("http://iecocms.eastus.cloudapp.azure.com/api/ieco-customer/v1/customers/customerDetails", new HttpEntity<CustomerDetailsRequest>(customerDetails, httpHeaders), OtpResponse.class);
		
		  System.out.println(otpResponse.getStatusCode());
		  System.out.println(otpResponse.getBody().getStatus());
		  if(otpResponse.getStatusCode().is2xxSuccessful()) {
			  if(otpResponse.getBody().getStatus().equalsIgnoreCase("200 OK")) {
				  return true;
			  }
			  else if(otpResponse.getBody().getStatus().equalsIgnoreCase("500 INTERNAL_SERVER_ERROR")) {
				  System.out.println(otpResponse.getBody());
				  return false;
			  }
		  }
		  return false;
		 
		
		
	}
	
	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		System.out.println(new CustomerDetailsService().getCustomerdetails());
	}
}
