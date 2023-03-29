package ieco.internal.middleware.feignclient;

import ieco.internal.middleware.domain.request.PanImageRequest;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.Path;

@Path("http://10.51.164.10:3000/kyc/fetchPanDtls")
@RegisterRestClient
public interface PanOcrClient {
    @PostMapping(value ="/", produces = MediaType.IMAGE_PNG_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    byte[] fetchImage(@RequestBody PanImageRequest panImageRequest);

}