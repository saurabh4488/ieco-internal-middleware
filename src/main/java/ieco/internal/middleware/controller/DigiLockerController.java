package ieco.internal.middleware.controller;

import ieco.internal.middleware.domain.request.DgCustomerReqVO;
import ieco.internal.middleware.domain.response.ResponseObject;
import ieco.internal.middleware.service.DigiLockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class DigiLockerController {

	@Autowired
	private DigiLockerService digiLockerService;
//	SonarQube issue: added "public" in method "getAuthorizationEndpoint"
	@GetMapping("/retrieveAuthorizationUrl")
	public ResponseEntity<ResponseObject> getAuthorizationEndpoint(@RequestParam("clientId") String clientId,@RequestParam("redirecturl") String redirectURL) {
		
		return new ResponseEntity<>(digiLockerService.retrieveAuthorizationUrl(clientId,redirectURL), HttpStatus.OK);
	}
	//	SonarQube issue: added "public" in method "getCustomerData"
	@PostMapping("/getCustomerData")
	public ResponseObject getCustomerData(@RequestBody @Valid DgCustomerReqVO req) {
		return digiLockerService.getCustomerData(req);
	}
}
