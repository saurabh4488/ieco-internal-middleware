package ieco.internal.middleware.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import ieco.internal.middleware.domain.request.C2CInsertDetails;
import ieco.internal.middleware.service.impl.Click2CallLoginService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;



@RestController
public class Click2CallController {
	private org.slf4j.Logger log = LoggerFactory.getLogger(Click2CallController.class);
	@Autowired
	private Click2CallLoginService click2CallLoginService;
	
	@PostMapping(value="/C2C")
	public <T> T click2call(@RequestBody @Valid C2CInsertDetails insertdetails,@RequestParam(value="type",required = false) String custType) throws JsonMappingException,JsonProcessingException,  UnsupportedEncodingException, GeneralSecurityException {
		log.info("In c2c controller");
		return  click2CallLoginService.login(insertdetails,custType);
	}
}
