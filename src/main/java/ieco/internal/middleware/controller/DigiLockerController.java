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
	
	@GetMapping("/retrieveAuthorizationUrl")
	ResponseEntity<ResponseObject> getAuthorizationEndpoint(@RequestParam("clientId") String clientId,@RequestParam("redirecturl") String redirectURL) {
		
		return new ResponseEntity<>(digiLockerService.retrieveAuthorizationUrl(clientId,redirectURL), HttpStatus.OK);
	}
	
	@PostMapping("/getCustomerData")
	ResponseObject getCustomerData(@RequestBody @Valid DgCustomerReqVO req) {
		return digiLockerService.getCustomerData(req);
	}
}
