package ieco.internal.middleware.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name="sms-client",url="${sms.uri}")
public interface SMSClient {
	
	
	
	@PostMapping("/SMSAPI")
	String sendSMS(String sendSMSRequest,@RequestHeader("Authorization") String token);
}
