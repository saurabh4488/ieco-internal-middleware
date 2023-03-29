package ieco.internal.middleware.feignclient;

import ieco.internal.middleware.domain.response.ZohoTokenGenResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import javax.ws.rs.Path;


@Path("https://accounts.zoho.in/oauth/v2")
@RegisterRestClient
public interface ZohoTokenGenClient {

	@PostMapping("${Zohotokengeneration}")
    ResponseEntity<ZohoTokenGenResponse> generateAccessToken();

}
