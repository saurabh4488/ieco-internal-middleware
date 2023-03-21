package ieco.internal.middleware.service;

import ieco.internal.middleware.domain.request.DeskContactCreationRequest;
import ieco.internal.middleware.domain.request.TPTicketUpdateRequest;
import ieco.internal.middleware.domain.request.TicketCreationRequest;
import ieco.internal.middleware.domain.response.ContactDetailsResponse;
import ieco.internal.middleware.domain.response.ResponseObject;
import ieco.internal.middleware.domain.response.TicketCreationResponse;
import org.springframework.http.ResponseEntity;

public interface ZohoService {

/**
 * 
 * @param value
 * @param searchField
 * @return
 */
public ResponseEntity<ContactDetailsResponse> searchContact(String value, String searchField);


/**
 * 
 * @param contactCreationReq
 * @return
 */
public <T> ResponseEntity<T> contactCreation(DeskContactCreationRequest contactCreationReq);

/**
 * 
 * @param middlewareRequest
 * @param contactId
 * @param IecoId
 * @return
 */
public TicketCreationResponse createTicket(TicketCreationRequest req);

public ResponseObject updateTicket(TPTicketUpdateRequest request);
	
}
