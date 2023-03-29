package ieco.internal.middleware.feignclient;

import ieco.internal.middleware.domain.response.GatewayTokenResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.ws.rs.Path;


@Path("https://apigwuat.kotak.com:8443")
@RegisterRestClient
public interface GatewayClient {
	
	@PostMapping("/auth/oauth/v2/token")
	GatewayTokenResponse getToken(MultiValueMap<String, Object> form);
	
	@PostMapping("/get_Ticket")
	String kbankSRCreation(String KbankSRCreationRequest,@RequestHeader("Authorization") String token);
	
	@PostMapping("/SMSAPI")
	String sendSMS(String sendSMSRequest,@RequestHeader("Authorization") String token);
}
