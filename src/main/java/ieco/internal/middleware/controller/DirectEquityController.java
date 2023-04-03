package ieco.internal.middleware.controller;

import ieco.internal.middleware.domain.request.AutoLoginDataRequestVO;
import ieco.internal.middleware.domain.request.AutoLoginRequestVO;
import ieco.internal.middleware.domain.request.IPORequestVO;
import ieco.internal.middleware.domain.response.AbstractResponse;
import ieco.internal.middleware.domain.response.ResponseObject;
import ieco.internal.middleware.enums.ResponseCodeEnum;
import ieco.internal.middleware.service.DirectEquityService;
import ieco.internal.middleware.util.AppConstant;
import ieco.internal.middleware.util.DirectEquityConstants;
import ieco.internal.middleware.util.DirectEquityUtil;
import ieco.internal.middleware.util.NullCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Map;

@RestController
public class DirectEquityController extends AbstractResponse {

    private Logger log = LoggerFactory.getLogger(DirectEquityController.class);


    @Autowired
    private DirectEquityUtil directEquityUtil;

    @Autowired
    private DirectEquityService directEquityService;

    /**
     * The autologin request type.
     */
    @Value("${ipo.autologin.requesttype}")
    private String autologinRequestType;

    /**
     * The autologin link id.
     */
    @Value("${ipo.autologin.linkid}")
    private int autologinLinkId;

    /**
     * The autologinapilink.
     */
    @Value("${ipo.autologin.apilink}")
    private String autologinapilink;

    /**
     * The ksec app source value.
     */
    @Value("${ksec.constant.appsource.val}")
    private int ksecAppSourceValue;

    @PostMapping(value = "/stocks/ipo/auto/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseObject> getIPOAutoLogin(@RequestHeader(value = "sessionId", required = true) String sessionId,
                                                          @RequestBody IPORequestVO request, HttpServletRequest httprequest) {
        Integer loggedInCustomerId = null;
        if (null != httprequest.getHeader(AppConstant.HEADER_CUSTOMER_ID_KEY)) {
            loggedInCustomerId = Integer.parseInt(httprequest.getHeader(AppConstant.HEADER_CUSTOMER_ID_KEY));
        }
        log.info("getIPOAutoLogin() starts for SessionID: {} and CustomerID: {}", sessionId, loggedInCustomerId);
        if (new NullCheck<>(request).with(IPORequestVO::getUserId).isNotNullOrEmpty()
                && new NullCheck<>(request).with(IPORequestVO::getClientId).isNotNullOrEmpty()) {

            log.info("Inside if IPORequestVO request {}", request);
            Map<String, String> appInitCacheInfo = directEquityUtil.getAppInitInfoFromCache(request.getClientId());
            if (appInitCacheInfo.get(DirectEquityConstants.DE_APPINIT_SESSION_ID) == null || appInitCacheInfo.get(DirectEquityConstants.DE_APPINIT_SESSION_ID).isEmpty())
                appInitCacheInfo.put(DirectEquityConstants.DE_APPINIT_SESSION_ID, sessionId);

            if (appInitCacheInfo.size() > 0) {
                log.info("Inside if appInitCacheInfo {}", appInitCacheInfo);
                AutoLoginDataRequestVO autoLoginDataRequestVO = AutoLoginDataRequestVO.builder()
                        .userId(request.getUserId())
                        .token(appInitCacheInfo.get(DirectEquityConstants.DE_APPINIT_SESSION_ID))
                        .linkId(autologinLinkId)
                        .appSource(ksecAppSourceValue)
                        .build();

                AutoLoginRequestVO autoLoginRequestVO = AutoLoginRequestVO.builder()
                        .requestType(autologinRequestType)
                        .data(autoLoginDataRequestVO)
                        .timeStamp(String.valueOf(Instant.now().toEpochMilli()))
                        .build();

                return directEquityService.getIPOAutoLogin(DirectEquityUtil.getFormedURL(
                                appInitCacheInfo.get(DirectEquityConstants.DE_APPINIT_REQUEST_MAP_SERVICE_HOST),
                                appInitCacheInfo.get(DirectEquityConstants.DE_APPINIT_REQUEST_MAP_SERVICE_PORT),
                                autologinapilink),
                        directEquityUtil.getEncryptedMessageForRequest(autoLoginRequestVO, appInitCacheInfo), loggedInCustomerId, appInitCacheInfo.get(DirectEquityConstants.DE_APPINIT_SESSION_ID));
            } else {
                log.info("Inside else DE_KSEC_APPINIT_FETCH_ERROR");
                return new ResponseEntity<>(responseError(DirectEquityConstants.APPINIT_INFO_FETCH_FAILURE,
                        ResponseCodeEnum.DE_KSEC_APPINIT_FETCH_ERROR), HttpStatus.OK);
            }

        } else {
            log.info("Inside else MISSING_REQUIRED_FIELD");
            return new ResponseEntity<>(responseError(DirectEquityConstants.MISSING_REQUIRED_FIELD,
                    ResponseCodeEnum.MISSING_REQUIRED_FIELD), HttpStatus.OK);
        }
    }
}