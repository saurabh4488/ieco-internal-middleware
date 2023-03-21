package ieco.internal.middleware.feignclient;

import ieco.internal.middleware.domain.request.KsecCRMMasterRequest;
import ieco.internal.middleware.domain.request.KsecTicketCreationRequest;
import ieco.internal.middleware.domain.response.KsecTicketCreationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="Ksec-ticket-creation-client",url="${ksecurl}")
public interface KsecTicketCreationClient {

	@PostMapping(value="/CRMQueryInsert")
	KsecTicketCreationResponse ksecTicketCreation(@RequestBody KsecTicketCreationRequest ksecTicketCreationRequest,@RequestHeader("ApiKey") String key);
	
	@PostMapping(value="/CRMMaster")
	KsecTicketCreationResponse ksecCRMmaster(@RequestBody KsecCRMMasterRequest ksecCRMMasterRequest,@RequestHeader("ApiKey") String key);
}
