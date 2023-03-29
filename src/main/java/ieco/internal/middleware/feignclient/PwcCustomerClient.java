package ieco.internal.middleware.feignclient;

import ieco.internal.middleware.domain.request.CustomerDetailsRequest;
import ieco.internal.middleware.domain.request.InitiateTransactionRequest;
import ieco.internal.middleware.domain.response.AllInCallCustomerDetailsResponseVO;
import ieco.internal.middleware.domain.response.CustomerDetailsResponse;
import ieco.internal.middleware.domain.response.ResponseObject;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RegisterRestClient
public interface PwcCustomerClient {
	
	@PostMapping("/v1/customers/customerDetails")
	ResponseEntity<CustomerDetailsResponse> getCustomerDetails(@RequestBody CustomerDetailsRequest customerDetailsRequest);

	@PostMapping("/v1/customers/initiatetransaction")
	ResponseEntity<ResponseObject> initiateTransaction(@RequestBody InitiateTransactionRequest req);

	@GetMapping("/v1/customers/internal/customer-details/{customerId}")
	public AllInCallCustomerDetailsResponseVO getCustomerDetailsInternal(@PathVariable("customerId") String customerId);


}
