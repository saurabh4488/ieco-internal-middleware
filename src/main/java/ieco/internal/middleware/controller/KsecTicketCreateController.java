package ieco.internal.middleware.controller;

import ieco.internal.middleware.domain.request.KsecTicketCreationRequest;
import ieco.internal.middleware.domain.response.KsecTicketCreationResponse;
import ieco.internal.middleware.service.impl.KsecQueryCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class KsecTicketCreateController {

	@Autowired
	private KsecQueryCreationService ksecQueryCreationService;
	/**
	 * SonarQube issue:
	 * made "ksecQueryCreate" method as public
	 */
	@PostMapping("/ksec/tickets/queryInsert")
	public KsecTicketCreationResponse ksecQueryCreate(
			@RequestBody @Valid KsecTicketCreationRequest ksecTicketCreationRequest) {
		return ksecQueryCreationService.createTicket(ksecTicketCreationRequest);
	}
}
