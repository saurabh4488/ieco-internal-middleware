package ieco.internal.middleware.controller;


import ieco.internal.middleware.domain.request.CalculatorRequest;
import ieco.internal.middleware.domain.response.CalculatorResponse;
import ieco.internal.middleware.service.impl.CalculatorService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CalculatorController {

	private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CalculatorService calculatorService;

	@PostMapping("/calculate")
	public  CalculatorResponse calculate(@Valid @RequestBody CalculatorRequest req) {
		log.info("Request received in Calc controller");

		return  calculatorService.calculate(req);
	}
	
	
}
