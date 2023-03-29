package ieco.internal.middleware.feignclient;

import ieco.internal.middleware.domain.response.GatewayTokenResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;

import javax.ws.rs.Path;

@Path("https://apigwuat.kotak.com:8443")
@RegisterRestClient
public interface GWAccessTokenClient {
	
	@PostMapping("/auth/oauth/v2/token")
	GatewayTokenResponse getToken(MultiValueMap<String, Object> form);
	
	
}
