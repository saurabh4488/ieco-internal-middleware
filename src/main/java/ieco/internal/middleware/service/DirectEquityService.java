package ieco.internal.middleware.service;

import ieco.internal.middleware.domain.response.ResponseObject;
import org.springframework.http.ResponseEntity;


// TODO: Auto-generated Javadoc

/**
 * The interface DirectEquityService service : APIs related to Direct Equity, stocks and securities functionality.
 */
public interface DirectEquityService {


    public ResponseEntity<ResponseObject> getIPOAutoLogin(String formedURL, String encryptedMessageForRequest, Integer loggedInCustomerId, String sessionId);

	
	
}
