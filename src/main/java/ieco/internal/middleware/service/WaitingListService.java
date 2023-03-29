package ieco.internal.middleware.service;


import ieco.internal.middleware.domain.request.IncomingEmail;
import ieco.internal.middleware.domain.request.PaginationVO;
import ieco.internal.middleware.domain.request.ProvideAccessVO;
import ieco.internal.middleware.domain.request.WaitingListDetailsVO;
import ieco.internal.middleware.domain.response.ResponseObject;

import javax.servlet.http.HttpServletResponse;


public interface WaitingListService {

	public ResponseObject createWaitingListNumber(IncomingEmail email);
	
	public ResponseObject verifyRefCode(String refCode);

	public ResponseObject updateDetails(WaitingListDetailsVO req);
	
	public ResponseObject saveInvitaionURL(IncomingEmail email);
	
	public ResponseObject checkAccess(String email);
	
	public ResponseObject findPaginated(PaginationVO request, String token);
	
	public ResponseObject findByEmailIdContaing(IncomingEmail email,String searchChars, String token);
	
	public ResponseObject login(IncomingEmail loginRequest);
	
	public ResponseObject provideAccess(ProvideAccessVO request, String token);
	
	public ResponseObject deleteUser(ProvideAccessVO request, String token);
	
	public ResponseObject logout(IncomingEmail loginRequest,String token);
	
	public void generateCsvResponse(HttpServletResponse response,String token);

	
	public ResponseObject reduceWaitingListNumber(String minValue, String token);
}
