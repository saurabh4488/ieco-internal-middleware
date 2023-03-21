package ieco.internal.middleware.service.impl;

import ieco.internal.middleware.domain.request.AppsFlyerPushRequest;
import ieco.internal.middleware.domain.response.AbstractResponse;
import ieco.internal.middleware.domain.response.ResponseObject;
import ieco.internal.middleware.enums.ResponseCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AppsFlyerServiceImpl extends AbstractResponse {
    private Logger log = LoggerFactory.getLogger(AppsFlyerServiceImpl.class);

    public ResponseObject pushApIService(AppsFlyerPushRequest appsFlyerPushRequest) {
        return responseSuccess("Records fetched successfully", ResponseCodeEnum.DATA_FOUND, appsFlyerPushRequest,"data");
    }
}