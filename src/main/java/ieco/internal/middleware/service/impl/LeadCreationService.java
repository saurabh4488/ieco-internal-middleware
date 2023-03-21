package ieco.internal.middleware.service.impl;/*package ieco.internal.middleware.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import ieco.internal.middleware.domain.request.LeadCreationData;
import ieco.internal.middleware.domain.request.LeadCreationReq;


public class LeadCreationService {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ZohoOAuth zohoOAuth;
	
	@Autowired
	private TicketCreationService ticketCreationService;
	
	public  String leadCreation(String company,String email,String lname,String phone) throws JsonMappingException, JsonProcessingException {
		String token=zohoOAuth.generateAccessToken();
		LeadCreationReq req = new LeadCreationReq();
		req.setCompany("KOTAK");
		req.setEmail("kai@kotak.com");
		req.setLast_Name("kai");
		req.setPhone("9000808086");
		req.setCustomer_Type("Contact");
		//req.setLayout("86758000000126711");
		List<LeadCreationReq> list =new ArrayList<>();
		list.add(req);
		LeadCreationData data = new LeadCreationData();
		data.setData(list);
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(data);
		System.out.println(json);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		httpHeaders.add("Authorization", "Zoho-oauthtoken " + token);

restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
restTemplate.setErrorHandler(new DefaultResponseErrorHandler() { public boolean hasError(ClientHttpResponse response) throws IOException { HttpStatus statusCode = response.getStatusCode(); return statusCode.series() == HttpStatus.Series.SERVER_ERROR; }
});
		HttpEntity<LeadCreationData> httpEntity = new HttpEntity<LeadCreationData>(data, httpHeaders);
		ResponseEntity<String> ccResponse = restTemplate.postForEntity("https://www.zohoapis.in/crm/v2/Contacts", httpEntity, String.class);
		System.out.println(ccResponse.getBody());
		ObjectMapper mapper = new ObjectMapper();
		JsonNode actualObj = mapper.readTree(ccResponse.getBody());
		System.out.println(actualObj.get("data").get(0).get("status").asText());
		if(actualObj.get("data").get(0).get("status").asText().equalsIgnoreCase("success") && actualObj.get("data").get(0).get("message").asText().equalsIgnoreCase("record added")) {
			String leadId=actualObj.get("data").get(0).get("details").get("id").asText();
			System.out.println(leadId);
			return ticketCreationService.createTicket(leadId,token);
			//return leadId;
		}
		return ccResponse.getBody();
	}
	
	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		//new LeadCreationService().leadCreation();
	}
}
*/