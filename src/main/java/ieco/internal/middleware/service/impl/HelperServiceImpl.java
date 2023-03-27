package ieco.internal.middleware.service.impl;


import ieco.internal.middleware.domain.request.CustomerTypeRequest;
import ieco.internal.middleware.domain.response.AbstractResponse;
import ieco.internal.middleware.domain.response.ResponseObject;
import ieco.internal.middleware.feignclient.UtilityServiceClient;
import ieco.internal.middleware.service.HelperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;

@Service
@Slf4j
public class HelperServiceImpl extends AbstractResponse implements HelperService {

    @Autowired
    UtilityServiceClient utilityServiceClient;
    @Override
    public ResponseEntity<HashMap<String,String>> getCustomerType(CustomerTypeRequest customerTypeRequest) {
        HashMap<String,String> res=new LinkedHashMap<>();
        try {
            ResponseEntity<HashMap<String, String>> getCustomerType = utilityServiceClient.getCustomerType(customerTypeRequest);
            log.info("getCustomer Type result is {}",getCustomerType);
            HashMap<String, String> customerTypeResponseBody=getCustomerType.getBody();
            if(customerTypeResponseBody!=null) {
                res.put("customerId",customerTypeResponseBody.get("customerId"));
                res.put("customerType",customerTypeResponseBody.get("customerType"));
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
            else {
                throw new NullPointerException("Customer Id or Customer Type Not Found ");
            }
        }
        catch(Exception e){
            log.info("Exception occurred in getCustomerType {}",e.getLocalizedMessage());
        }
        log.info("Failed to get customer Type ");
        res.put("status", "Error");
        res.put("result", "Customer Type not found");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}
