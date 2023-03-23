package ieco.internal.middleware.controller;

import ieco.internal.middleware.domain.request.KbankSRCreationRequest;
import ieco.internal.middleware.domain.response.KbankSRCreationResponse;
import ieco.internal.middleware.service.impl.KbankSRCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KbankSRCreationController {

	@Autowired
	private KbankSRCreationService kbankSRCreationService;
	
	@PostMapping("/siebel/SRcreation")
	public KbankSRCreationResponse kbnkSRCreation(@RequestBody KbankSRCreationRequest kbankSRCreationRequest){
		return kbankSRCreationService.kbnkSRCreation(kbankSRCreationRequest);
	}
}
