package ieco.internal.middleware.feignclient;

import ieco.internal.middleware.domain.response.ZohoTokenGenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name="tokenGenclient",url="https://accounts.zoho.in/oauth/v2")
public interface ZohoTokenGenClient {

	@PostMapping("${Zohotokengeneration}")
    ResponseEntity<ZohoTokenGenResponse> generateAccessToken();
}
