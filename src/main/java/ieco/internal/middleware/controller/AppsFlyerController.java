package ieco.internal.middleware.controller;

import ieco.internal.middleware.domain.request.AppsFlyerPushRequest;
import ieco.internal.middleware.domain.request.AppsFlyerRequest;
import ieco.internal.middleware.domain.response.ResponseObject;
import ieco.internal.middleware.service.impl.AppsFlyerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class AppsFlyerController {
    private Logger log = LoggerFactory.getLogger(AppsFlyerController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppsFlyerServiceImpl appsFlyerService;

    @Value("${app_id_placeholder}")
    private String appIdPlaceholder;

    @Value("${ios_app_id_placeholder}")
    private String ios_app_id_placeholder;

    @Value("${dev_key_placeholder}")
    private String dev_key_placeholder;

    @Value("${appsflyer_inAppEvents_url}")
    private String appsflyer_inAppEvents_url;

    @Value("${isAppsFlyerEnabled}")
    private boolean isAppsFlyerEnabled;

    @PostMapping(value = "/appsFlyer/inAppEvents")
    public ResponseEntity<ResponseObject> inAppEvents(@RequestBody AppsFlyerRequest appsFlyerRequest, @RequestHeader(value = "platform", required = false) String platform) {
        try{
            String restCall=null;
            log.info("Start of inAppEvents");
            log.info("appsFlyerRequest - {} - platform = {}", appsFlyerRequest,platform);
            ResponseObject responseObject = new ResponseObject();
            log.info("platform - {}", platform);
            String appId = "";
                if(platform != null && platform.equals("IOS")){
                    appId = ios_app_id_placeholder;
                }else{
                    appId = appIdPlaceholder;
                }
            log.info("appId - {}", appId);
            if (isAppsFlyerEnabled) {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add("ContentType", "application/json");
                httpHeaders.add("authentication", dev_key_placeholder);
                ResponseEntity<String> response = this.restTemplate.postForEntity(appsflyer_inAppEvents_url+appId, new HttpEntity<>(appsFlyerRequest, httpHeaders), String.class);
                HttpStatus status = response.getStatusCode();
                /**
                 * SonarQube issue start : // String restCall = response.getBody().toUpperCase();
                 */
                String body = response.getBody();
                if (body!=null){
                    restCall=body.toUpperCase();
                }

                /**
                 * SonarQube issue end
                 */

                log.info("reponse status : {} - platform - {} - appId = {} - event_name = {} - customer_id = {}", status,platform,appId,appsFlyerRequest.getEventName(),appsFlyerRequest.getCustomer_user_id());
                log.info("reponse restCall : {}- platform - {} - appId - {}", restCall,platform,appId);
                responseObject.setTimeStamp(System.currentTimeMillis());
                responseObject.setResponseCode(status.toString());
                responseObject.setStatus(HttpStatus.OK.toString());
                responseObject.setMessage(restCall);
            }else{
                responseObject.setTimeStamp(System.currentTimeMillis());
                responseObject.setResponseCode("200 OK");
                responseObject.setStatus(HttpStatus.OK.toString());
                responseObject.setMessage("200 OK");
            }
            log.info("End of inAppEvents");
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        }catch (Exception e){
            log.error("Exception in inAppEvents {0}.", e); // sonar issue changed {} to {0}.
        }
        return new ResponseEntity<>(new ResponseObject(), HttpStatus.OK);
    }

    @PostMapping(value = "/appsFlyer/pushApi")
    public ResponseEntity<ResponseObject> pushApi(@RequestBody AppsFlyerPushRequest appsFlyerPushRequest) throws UnknownHostException, IOException {
        log.info("request for appsFlyerPushRequest {}", appsFlyerPushRequest);
        return new ResponseEntity<>(appsFlyerService.pushApIService(appsFlyerPushRequest), HttpStatus.OK);
    }


}