package ieco.internal.middleware.controller;

import ieco.internal.middleware.domain.request.IECOLeadCreationRequest;
import ieco.internal.middleware.domain.response.ZohoContactCreationResponse;
import ieco.internal.middleware.service.impl.DeskContactCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class IECOLeadCreationController {
	
	@Value("${zohoenv}")
	private String environment;

	@Value("${isZohoEnabled}")
	private boolean isZohoEnable;
	
	@Autowired
	private DeskContactCreationService DeskContactCreationService;
	
	@PostMapping("/zoho/contacts")
	ZohoContactCreationResponse leadCreate(@RequestBody @Valid IECOLeadCreationRequest iecoLeadCreationRequest ){
		if(isZohoEnable) {
			return DeskContactCreationService.contactCreation(iecoLeadCreationRequest);
		} else {
			ZohoContactCreationResponse res = ZohoContactCreationResponse.builder().email(iecoLeadCreationRequest.getEmail()).
					status("Success").firstName(iecoLeadCreationRequest.getFirstName()).build();
			return res;
		}
	}
}
