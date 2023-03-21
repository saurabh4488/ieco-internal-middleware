package ieco.internal.middleware.service;


import ieco.internal.middleware.domain.request.DgCustomerReqVO;
import ieco.internal.middleware.domain.response.ResponseObject;

public interface DigiLockerService {

	ResponseObject retrieveAuthorizationUrl(String clientId,String redirectURL);
	
	ResponseObject getCustomerData(DgCustomerReqVO req);
}
