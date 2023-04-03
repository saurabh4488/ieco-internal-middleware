package ieco.internal.middleware.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ieco.internal.middleware.domain.response.AbstractResponse;
import ieco.internal.middleware.domain.response.AllInCallCustomerDetailsResponseVO;
import ieco.internal.middleware.domain.response.ResponseObject;
import ieco.internal.middleware.enums.ResponseCodeEnum;
import ieco.internal.middleware.feignclient.PwcCustomerClient;
import ieco.internal.middleware.feignclient.RedisCacheClient;
import ieco.internal.middleware.service.DirectEquityService;
import ieco.internal.middleware.util.DirectEquityUtil;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.DataException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import java.util.Map;

import static ieco.internal.middleware.util.DirectEquityConstants.*;

@Service
@Slf4j
public class DirectEquityServiceImpl extends AbstractResponse implements DirectEquityService {


    @Autowired
    private DirectEquityUtil directEquityUtil;



    @Value("${api.status.httperrorstatus}")
    private String httperrorStatus;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private PwcCustomerClient customerClient;

    @Autowired
    private RedisCacheClient redisCacheClient;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public ResponseEntity<ResponseObject> getIPOAutoLogin(String formedURL, String encryptedMessageForRequest, Integer loggedinCustomerId, String sessionId) {
        log.info("Inside getIPOAutoLogin method");
        try {
            String requiredUrl = formedURL + QUES_MARK + encryptedMessageForRequest;
            log.info("Url formed is {}", requiredUrl);
            log.info("referenceId:{}", sessionId);
            AllInCallCustomerDetailsResponseVO response = customerClient.getCustomerDetailsInternal(loggedinCustomerId.toString());
            if (null != response && null != response.getEmail() && !response.getEmail().equalsIgnoreCase("string")) {
                String email = response.getEmail();
                log.info("Email retrieved is [EMAIL] {}", email);
                Object object = redisCacheClient.getObjectFromCache(email);
                String respJson = objectMapper.writeValueAsString(object);
                Map<String, String> cacheMap = objectMapper.readValue(respJson, Map.class);
                log.info("cacheMap retrieved is [cacheMap] {}", cacheMap);

                return new ResponseEntity<>(responseSuccess(IPO_API_SUCCESS,
                        ResponseCodeEnum.IPO_API_SUCCESS_STATUS, requiredUrl, IPO_API_INFO), HttpStatus.OK);
            }

            return new ResponseEntity<>(responseSuccess(IPO_API_FAILURE,
                    ResponseCodeEnum.IPO_CASE_API_FAILURE, requiredUrl, IPO_API_INFO), HttpStatus.OK);

        } catch (InternalServerError | IllegalArgumentException | FeignException
                | DataException | JsonProcessingException e) {
            log.error("Error in method getIPOAutoLogin -{}", e);
            return new ResponseEntity<>(
                    responseError(IPO_API_FAILURE + e.getMessage(), ResponseCodeEnum.IPO_CASE_API_FAILURE),
                    HttpStatus.valueOf(Integer.parseInt(httperrorStatus)));
        }
    }

}