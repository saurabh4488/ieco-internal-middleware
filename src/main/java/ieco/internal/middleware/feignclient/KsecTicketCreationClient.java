package ieco.internal.middleware.feignclient;

import ieco.internal.middleware.domain.request.KsecCRMMasterRequest;
import ieco.internal.middleware.domain.request.KsecTicketCreationRequest;
import ieco.internal.middleware.domain.response.KsecTicketCreationResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.ws.rs.Path;

@Path("https://uatweb.kotakseconline.com/KSBusinessLayerFrwk/Chatbot")
@RegisterRestClient
public interface KsecTicketCreationClient {

	@PostMapping(value="/CRMQueryInsert")
	KsecTicketCreationResponse ksecTicketCreation(@RequestBody KsecTicketCreationRequest ksecTicketCreationRequest,@RequestHeader("ApiKey") String key);
	
	@PostMapping(value="/CRMMaster")
	KsecTicketCreationResponse ksecCRMmaster(@RequestBody KsecCRMMasterRequest ksecCRMMasterRequest,@RequestHeader("ApiKey") String key);
}
