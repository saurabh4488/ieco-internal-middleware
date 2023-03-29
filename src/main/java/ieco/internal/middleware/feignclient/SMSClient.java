package ieco.internal.middleware.feignclient;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.ws.rs.Path;

@Path("https://apigwuat.kotak.com:8443")
@RegisterRestClient
public interface SMSClient {

	@PostMapping("/SMSAPI")
	String sendSMS(String sendSMSRequest,@RequestHeader("Authorization") String token);
}
