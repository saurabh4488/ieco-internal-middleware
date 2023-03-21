package ieco.internal.middleware.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import ieco.internal.middleware.domain.request.CustomerDetailsRequest;
import ieco.internal.middleware.domain.request.DeskContactCreationCustomFields;
import ieco.internal.middleware.domain.request.KbankSRCreationRequest;
import ieco.internal.middleware.domain.request.ZohoTicketUpdateRequest;
import ieco.internal.middleware.domain.response.CustomerDetailsResponse;
import ieco.internal.middleware.domain.response.KbankSRCreationResponse;
import ieco.internal.middleware.domain.response.TicketUpdateResponse;
import ieco.internal.middleware.feignclient.GatewayClient;
import ieco.internal.middleware.feignclient.PwcCustomerClient;
import ieco.internal.middleware.feignclient.ZohoClient;
import ieco.internal.middleware.util.AESCBCPKCS5Encryption;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

@Service
public class KbankSRCreationService {
	private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private GatewayClient gatewayClient;

	@Autowired
	private PwcCustomerClient iecoClient;
	
	@Autowired
	private ZohoClient zohoClient;
	
	@Value("${orgId}")
	private String orgid;
	@Value("${siebel_client_secret}")
	private String siebelClientSecret;
	
	@Value("${siebel_client_id}")
	private String siebelClientId;
	
	public KbankSRCreationResponse kbnkSRCreation(KbankSRCreationRequest kbankSRCreationRequest){
		log.info("req from zoho {}",kbankSRCreationRequest);
		try {
			CustomerDetailsRequest customerDetailsReq = new CustomerDetailsRequest();
			customerDetailsReq.setCustomerId(Integer.parseInt(kbankSRCreationRequest.getKotak360Id()));
			ResponseEntity<CustomerDetailsResponse> custDetailsResEntity = iecoClient.getCustomerDetails(customerDetailsReq);
			
			CustomerDetailsResponse custDetailsRes = custDetailsResEntity.getBody();
			log.info("CustomerDetailsResponse from PwC {}",custDetailsRes);
			if (custDetailsRes.getStatus().equalsIgnoreCase("200 OK")) {
				kbankSRCreationRequest.setCrn(custDetailsRes.getAttrs().getUserDetails().getBankCrn());
				//kbankSRCreationRequest.setCrn("");
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				 
				
				 String	kbankSRCreationencResponse = gatewayClient.kbankSRCreation(AESCBCPKCS5Encryption.encrypt(ow.writeValueAsString(kbankSRCreationRequest), siebelClientSecret),"Bearer "+getAccessToken());
			//System.out.println("KbankSRCreationencResponse "+KbankSRCreationencResponse);
				ObjectMapper mapper = new ObjectMapper();
				//System.out.println(ow.writeValueAsString(AESCBCPKCS5Encryption.decrypt(ow.writeValueAsString(KbankSRCreationencResponse.trim()), siebelClientSecret)));
				String decRes= AESCBCPKCS5Encryption.decrypt(kbankSRCreationencResponse.trim(), siebelClientSecret);
				KbankSRCreationResponse kbankSRCreationResponse = mapper.readValue(decRes, KbankSRCreationResponse.class);
				log.info("KbankSRCreationResponse from kbank {}",kbankSRCreationResponse);
				if(kbankSRCreationResponse.getReturnMessage().equalsIgnoreCase("Success")) {
					ZohoTicketUpdateRequest zohoTicketUpdateRequest = new ZohoTicketUpdateRequest();
					DeskContactCreationCustomFields cf = new DeskContactCreationCustomFields();
					cf.setCf_siebel_ticket_id(kbankSRCreationResponse.getSrNumber());
					zohoTicketUpdateRequest.setCf(cf);
					log.info("req for updating zoho ticket details {}",zohoTicketUpdateRequest);
					TicketUpdateResponse tktUpdateRes = zohoClient.ticketUpdate(zohoTicketUpdateRequest, kbankSRCreationResponse.getTicketId());
					log.info("res for updating zoho ticket details {}",tktUpdateRes);
					return kbankSRCreationResponse;
				}
				return kbankSRCreationResponse;
			}else {
				log.info("Customer is not found in ieco platform {}",kbankSRCreationRequest.getKotak360Id());
				KbankSRCreationResponse kbankSRCreationResponse = new KbankSRCreationResponse();
				kbankSRCreationResponse.setErrorMsg("Customer is not found in ieco platform");
				kbankSRCreationResponse.setStatus("Failure");
				return kbankSRCreationResponse;
			}
			//return  gatewayClient.kbankSRCreation(kbankSRCreationRequest);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.info("Contact not found in IECO with ID:{}",kbankSRCreationRequest.getKotak360Id());
		KbankSRCreationResponse KbankSRCreationResponse = new KbankSRCreationResponse();
	
		KbankSRCreationResponse.setStatus("Failure");
		KbankSRCreationResponse.setReturnMessage("Technical Failure");
		return KbankSRCreationResponse;
		
		//return  gatewayClient.kbankSRCreation(kbankSRCreationRequest);
	}
	
	String getAccessToken() {
		MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
		 form.add("client_id", siebelClientId);
		 form.add("client_secret", siebelClientSecret);
		 form.add("grant_type", "client_credentials");
		 
		return gatewayClient.getToken(form).getAccess_token();
	}
}
