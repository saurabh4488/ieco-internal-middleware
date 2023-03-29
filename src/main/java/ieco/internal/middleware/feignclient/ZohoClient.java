package ieco.internal.middleware.feignclient;


import ieco.internal.middleware.domain.request.DeskContactCreationRequest;
import ieco.internal.middleware.domain.request.TicketCreationRequest;
import ieco.internal.middleware.domain.response.*;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.HeaderParam;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;


@Path("https://desk.zoho.in/api/v1")
@RegisterRestClient
public interface ZohoClient {

	@PutMapping("/tickets/{id}")
	<T> TicketUpdateResponse ticketUpdate(@RequestBody T zohoTicketUpdateRequest, @PathVariable String id
			);

	@PostMapping("/contacts")
	ZohoContactCreationResponse contactCreate(@RequestBody DeskContactCreationRequest deskContactCreationRequest
			);
	
	
	@PutMapping("/contacts/{id}")
	ZohoContactCreationResponse contactUpdate(@RequestBody DeskContactCreationRequest deskContactCreationRequest,
	@PathVariable String id);

	@GetMapping("/contacts/search")
	ResponseEntity<ContactDetailsResponse> searchContact(@RequestParam(value="customField1") String key) ;
	
	@PostMapping("/tickets")
	TicketCreationResponse ticketCreate(TicketCreationRequest req) ;
	
	//https://desk.zoho.in/api/v1/tickets/17634000003725003/attachments
		
	@PostMapping(value="/tickets/{id}/attachments",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@HeaderParam("Content-Type: multipart/form-data")
	ZohoAttachmentResponse ticketAttachment(@RequestParam("file") FileUpload file, @PathVariable String id) ;
	
	@GetMapping("/tickets/{id}")
	TicketUpdateResponse getTicket(@PathVariable String id);
	
//	class ZohoClientConfig  {
//
//		@Autowired
//		private ZohoUtility utility;
//
//
//		@Value("${orgId}")
//		private String orgId;
//
//	    @Bean
//	    Logger.Level feignLoggerLevel() {
//	        return Logger.Level.FULL;
//	    }
//
//	    @Bean
//	    public RequestInterceptor requestInterceptor() {
//
//
//	        return requestTemplate -> {
//
//	        	requestTemplate.header("Authorization","Zoho-oauthtoken "+utility.getToken());
//	            requestTemplate.header("orgId",orgId);
//
//
//	        };
//	    }
//
//	}


}
