package ieco.internal.middleware.feignclient;

import ieco.internal.middleware.domain.request.CustomerDetailsRequest;
import ieco.internal.middleware.domain.response.OtpResponse;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.ws.rs.Path;

@Path("http://iecocms.eastus.cloudapp.azure.com/api/ieco-customer/v1")
@RegisterRestClient
public interface CustomerDetailsClient {

    @PostMapping("/customers/customerDetails")
    ResponseEntity<OtpResponse> getOtpResponse(@RequestBody CustomerDetailsRequest customerDetailsRequest, @RequestHeader(value = "ContentType") String contentType,@RequestHeader(value = "Authorization") String authorization);
}
