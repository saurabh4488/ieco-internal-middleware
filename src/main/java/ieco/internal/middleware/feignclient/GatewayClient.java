package ieco.internal.middleware.feignclient;

import ieco.internal.middleware.domain.response.GatewayTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name="gateway-client",url="${gatewaybaseurl}")
public interface GatewayClient {
	
	@PostMapping("/auth/oauth/v2/token")
	GatewayTokenResponse getToken(MultiValueMap<String, Object> form);
	
	@PostMapping("/get_Ticket")
	String kbankSRCreation(String KbankSRCreationRequest,@RequestHeader("Authorization") String token);
	
	@PostMapping("/SMSAPI")
	String sendSMS(String sendSMSRequest,@RequestHeader("Authorization") String token);
}
