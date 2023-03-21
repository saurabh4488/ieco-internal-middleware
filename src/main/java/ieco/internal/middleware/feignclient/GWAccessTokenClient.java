package ieco.internal.middleware.feignclient;

import ieco.internal.middleware.domain.response.GatewayTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name="gwaccesstoken", url="${sms.accesstoken}")
public interface GWAccessTokenClient {
	
	@PostMapping("/auth/oauth/v2/token")
	GatewayTokenResponse getToken(MultiValueMap<String, Object> form);
	
	
}
