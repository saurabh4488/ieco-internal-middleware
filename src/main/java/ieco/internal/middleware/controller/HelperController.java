package ieco.internal.middleware.controller;


import ieco.internal.middleware.domain.request.CustomerTypeRequest;
import ieco.internal.middleware.service.HelperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@Slf4j
@RequestMapping("/helper")
public class HelperController {
    @Autowired
    private HelperService helperService;

    @PostMapping("/getCustomerType")
    ResponseEntity<HashMap<String, String>> getCustomerType(@RequestBody @Valid CustomerTypeRequest customerTypeRequest) {
        log.info("Inside get customerType controller");
        return helperService.getCustomerType(customerTypeRequest);
    }
}
