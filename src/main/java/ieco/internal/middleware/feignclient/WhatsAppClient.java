package ieco.internal.middleware.feignclient;

import ieco.internal.middleware.domain.response.WhatsAppPushMessageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "whatsapp-client",url="https://media.smsgupshup.com/GatewayAPI/rest")
public interface WhatsAppClient {
	
	@GetMapping
	void optIn(@RequestParam(value="method") String method,
            @RequestParam(value="format") String format,
            @RequestParam(value="userid") String userid,
            @RequestParam(value="password") String password,
            @RequestParam(value="phone_number") String send_to,
            @RequestParam(value="v") String v,
            @RequestParam(value="auth_scheme") String auth_scheme,
            @RequestParam(value="channel") String channel
            );
	
	

	@GetMapping
	WhatsAppPushMessageResponse pushMessage(@RequestParam(value="method") String method,
            @RequestParam(value="format") String format,
            @RequestParam(value="userid") String userid,
            @RequestParam(value="password") String password,
            @RequestParam(value="send_to") String send_to,
            @RequestParam(value="v") String v,
            @RequestParam(value="auth_scheme") String auth_scheme,
            @RequestParam(value="msg_type") String msg_type,
            @RequestParam(value="msg") String msg);

	@GetMapping
	WhatsAppPushMessageResponse pushEncryptedMessage(@RequestParam(value="userid") String userid,
													 @RequestParam(value="encrdata") String encrdata);

	@GetMapping
	void optinEncrp(@RequestParam(value="userid") String userid,
					@RequestParam(value="encrdata") String encrdata);
}
