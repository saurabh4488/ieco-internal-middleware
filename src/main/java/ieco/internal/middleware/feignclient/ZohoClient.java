package ieco.internal.middleware.feignclient;


import ieco.internal.middleware.domain.request.DeskContactCreationRequest;
import ieco.internal.middleware.domain.request.TicketCreationRequest;
import ieco.internal.middleware.domain.response.*;
import ieco.internal.middleware.util.ZohoUtility;
import feign.Headers;
import feign.Logger;
import feign.Param;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@FeignClient(name = "zoho-client", url = "${zohobaseurl}",configuration=ZohoClient.ZohoClientConfig.class)
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
	@Headers("Content-Type: multipart/form-data")
	ZohoAttachmentResponse ticketAttachment(@Param("file") MultipartFile file,@PathVariable String id) ;
	
	@GetMapping("/tickets/{id}")
	TicketUpdateResponse getTicket(@PathVariable String id);
	
	class ZohoClientConfig  {
		
		@Autowired
		private ZohoUtility utility;
		
		
		@Value("${orgId}")
		private String orgId;
		
	    @Bean
	    Logger.Level feignLoggerLevel() {
	        return Logger.Level.FULL;
	    }
	    
	    @Bean
	    public RequestInterceptor requestInterceptor() {
	    	
	    	
	        return requestTemplate -> {
	        	
	        	requestTemplate.header("Authorization","Zoho-oauthtoken "+utility.getToken());
	            requestTemplate.header("orgId",orgId);
	            
	           
	        };
	    }
	    
	}


}
