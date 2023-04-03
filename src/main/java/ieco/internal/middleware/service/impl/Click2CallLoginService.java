package ieco.internal.middleware.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import ieco.internal.middleware.domain.request.C2CInsertDetails;
import ieco.internal.middleware.domain.request.Click2CallLogin;
import ieco.internal.middleware.domain.response.C2CInsertResponse;
import ieco.internal.middleware.domain.response.C2CLoginResponse;
import ieco.internal.middleware.domain.response.CustomerDetailsResponse;
import ieco.internal.middleware.util.AESCBCPKCS5Encryption;
import ieco.internal.middleware.util.RestUtility;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

@Component
public class Click2CallLoginService {

	private org.slf4j.Logger log = LoggerFactory.getLogger(Click2CallLoginService.class);
	
	public final String defaultClientCode = "123nw";
	@Autowired
	private TokenGeneration tokenGeneration;

	
	@Value("${c2cLoginID}")
	private String c2cLoginID;
	
	@Autowired
	private RestUtility restUtility;
	
	@Value("${iecoToken}")
	private String iecoToken;
	
	@Autowired
	private OtpService otpService;
	
	@Value("${c2cLoginPassword}")
	private String c2cLoginPassword;
	
	@Value("${c2cLoginURL}")
	private String c2cLoginURL;
	
	@Value("${c2cInsertDetailsURL}")
	private String c2cInsertDetailsURL;
	
	@Value("${encKey}")
	private String encKey;
	
	@Value("${customerDetails}")
	private String getCustURL;
	
	@SuppressWarnings("unchecked")
	public <T> T login(C2CInsertDetails insertdetails, String custType) throws JsonProcessingException,JsonMappingException,
			UnsupportedEncodingException, GeneralSecurityException {
		Click2CallLogin loginRequest = new Click2CallLogin();
		loginRequest.setLogin_ID(c2cLoginID);
		loginRequest.setLogin_Pwd(Base64.encodeBase64String(c2cLoginPassword.getBytes()));
		loginRequest.setClient_IP("");
		loginRequest.setChannel_ID("");
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String writeValueAsString = ow.writeValueAsString(insertdetails);
		log.info("C2C insert Request Recevied from zoho: {}.", writeValueAsString);
		String loginRequestLog = ow.writeValueAsString(loginRequest);
		log.info("C2C login Request: {}.", loginRequestLog);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<String> loginResponse = restUtility.post(c2cLoginURL, null,
				AESCBCPKCS5Encryption.encrypt(ow.writeValueAsString(loginRequest), encKey), String.class);
		log.info("C2C login response: {}.",loginResponse);
		
		if (restUtility.checkStatus(loginResponse) ) {
			log.info(loginResponse.getBody());
			ObjectMapper mapper = new ObjectMapper();
			String writeValueAsStringLog = ow.writeValueAsString(loginResponse.getBody());
			log.info("writevalueAsString value- {}", writeValueAsStringLog);
			C2CLoginResponse C2CLoginRes = mapper.readValue(loginResponse.getBody(), C2CLoginResponse.class);
			
			if( C2CLoginRes.getError_Code() != null && !C2CLoginRes.getError_Code().isEmpty() &&C2CLoginRes.getError_Code().equals("200") ) {
			C2CInsertResponse insertdetailsResponse = c2cInsertDetails(loginResponse.getBody(), insertdetails,
					custType);
			log.info("C2C insert details response {}" , insertdetailsResponse);
			return (T) insertdetailsResponse;
			}else {
				log.info("C2C login failed {}" , loginResponse);
			return (T) C2CLoginRes;
			}
		}

		
		return null;
		
	}
	public <T> T c2cInsertDetails(String loginResponse, C2CInsertDetails insertdetails, String custType)
			throws JsonProcessingException, JsonMappingException,  UnsupportedEncodingException,
			GeneralSecurityException {
		
		if (custType != null && !custType.isEmpty() && custType.equalsIgnoreCase("Contact") || !insertdetails.getCustomer_ID().equals("1234")  ) {
			CustomerDetailsResponse custDetailsRes = otpService.getCustomerdetails(Integer.parseInt(insertdetails.getCustomer_ID()), null);
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			log.info("C2C Customer details: {}.",ow.writeValueAsString(custDetailsRes));
			if (custDetailsRes.getStatus().equalsIgnoreCase("200 OK")) {
				
				insertdetails.setMobile_Number(custDetailsRes.getAttrs().getUserDetails().getMobile());
				if(insertdetails.getEntity().equalsIgnoreCase("K-sec") && custDetailsRes.getAttrs().getUserDetails().getKsecClientCode()!=null ) {
				String clientCode =insertdetails.getCrm_Ticket_ID()+"$$"+custDetailsRes.getAttrs().getUserDetails().getKsecClientCode();
					insertdetails.setCrm_Ticket_ID(clientCode);
				}
			}
		}
		else if(insertdetails.getEntity().equalsIgnoreCase("K-sec") || insertdetails.getCustomer_ID().equals("1234")) {
		String clientCode =insertdetails.getCrm_Ticket_ID()+"$$"+defaultClientCode;
		insertdetails.setCrm_Ticket_ID(clientCode);
		}
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		log.info("C2C insert Request: {}.",ow.writeValueAsString(insertdetails));
		Map<String,String> mapHeaders = new HashMap<String, String>();
		mapHeaders.put("Authorization", "Bearer " + new ObjectMapper().readTree(loginResponse).get("Token").asText());
		ResponseEntity<String> insertdetailsResponse=restUtility.post(c2cInsertDetailsURL, mapHeaders, AESCBCPKCS5Encryption.encrypt(ow.writeValueAsString(insertdetails), encKey), String.class);
		C2CInsertResponse C2CInsertRes = null;
		
		if(restUtility.checkStatus(insertdetailsResponse) ) {
			log.info(insertdetailsResponse.getBody());
			ObjectMapper mapper = new ObjectMapper();
			 C2CInsertRes = mapper.readValue(insertdetailsResponse.getBody(), C2CInsertResponse.class);
			
			if( C2CInsertRes.getError_Code() != null && !C2CInsertRes.getError_Code().isEmpty() &&C2CInsertRes.getError_Code().equals("200") && C2CInsertRes.getError_Description().equals("Success")) {
				
				log.info("C2C is done for the ID:{}" , insertdetails.getCustomer_ID());
			return  (T) C2CInsertRes;
			}
			}
				log.info("C2C insert details response {}" , insertdetailsResponse.getBody());
				log.info("C2C is failed for the ID:{}" , insertdetails.getCustomer_ID());
				return  (T) C2CInsertRes;
			
		
	}
}
