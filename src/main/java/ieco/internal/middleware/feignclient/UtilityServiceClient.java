package ieco.internal.middleware.feignclient;

import ieco.internal.middleware.domain.request.CustomerTypeRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;

//@FeignClient(name="utils-service-client",url="${utils.customertype.url}")
@FeignClient(name = "ieco-utility")
public interface UtilityServiceClient {

    @PostMapping("/v1/helper/getCustomerType")
    ResponseEntity<HashMap<String,String>> getCustomerType(@RequestBody CustomerTypeRequest customerTypeRequest);

}

