package ieco.internal.middleware.service;

import ieco.internal.middleware.domain.request.CustomerTypeRequest;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public interface HelperService {
    ResponseEntity<HashMap<String,String>> getCustomerType(CustomerTypeRequest customerTypeRequest);
}
