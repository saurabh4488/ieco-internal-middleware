package ieco.internal.middleware.controller;

import ieco.internal.middleware.domain.request.ClevertapEventsRequest;
import ieco.internal.middleware.domain.response.ClevertapEventsResponse;
import ieco.internal.middleware.feignclient.CleverTapClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clevertap")
public class ClevertapController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CleverTapClient cleverTapClient;

    @Value("${cleverTapAccountId}")
    private String cleverTapAccountId;

    @Value("${cleverTapPasscode}")
    private String cleverTapPasscode;

    @PostMapping("/uploadEvent")
    //@PostMapping("/fetchPanDtls")
    public ClevertapEventsResponse cleverTapEvent(@RequestBody ClevertapEventsRequest clevertapEventsRequest){
        log.info("clevertapEventsRequest received req {}",clevertapEventsRequest);
        try{
            log.info("clevertapEventsRequest finalClevertapEventsReq {}",clevertapEventsRequest);
            ClevertapEventsResponse responseEntity = cleverTapClient.uploadCleverTapEvents(clevertapEventsRequest,cleverTapAccountId,cleverTapPasscode);
            log.info("ClevertapEventsResponse : {}",responseEntity);
            return responseEntity;
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info("Something went wrong");
        ClevertapEventsResponse clevertapEventsResponse = ClevertapEventsResponse.builder().build();
        return clevertapEventsResponse;
    }
}
