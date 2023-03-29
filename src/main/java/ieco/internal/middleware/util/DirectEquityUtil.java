package ieco.internal.middleware.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ieco.internal.middleware.domain.response.DEAppinitResponseVO;
import ieco.internal.middleware.domain.response.AbstractResponse;
import ieco.internal.middleware.exception.CustomerErrorCode;
import ieco.internal.middleware.exception.DirectEquityExceptionFactory;
import ieco.internal.middleware.feignclient.RedisCacheClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Component
public class DirectEquityUtil extends AbstractResponse {

    private static String protocol;
    @Autowired
    private RedisCacheClient redisCacheClient;

    @Autowired
    public ObjectMapper objectMapper;
    /**
     * The log.
     */
    private Logger log = LoggerFactory.getLogger(DirectEquityUtil.class);
    @Value("${ipo.autologin.weblinkHost}")
    private String weblinkHost;
    /**
     * Fortify issue
     * Taken ENCRYPTION_PUBLIC_KEY from application property file , instead of DirectEquityConstants class
     */
    @Value("${ENCRYPTION_PUBLIC_KEY}")
    private String ENCRYPTION_PUBLIC_KEY;

    public static String getFormedURL(String host, String port, String api) {
        if (!StringUtils.isEmpty(port))
            return protocol + host + DirectEquityConstants.COLON + port + api;
        else
            return protocol + host + api;
    }

    @Value("${ksec.appinit.protocol}")
    public synchronized void setProtocol(String url) {
        protocol = url;
    }

    public <T> String getEncryptedMessageForRequest(T request, Map<String, String> appInitCacheInfo) {
        String encodedGenericRequestString = "";
        try {
            if (DirectEquityConstants.DE_APPINIT_REQUEST_MAP_ENCODE_BASE64.equalsIgnoreCase(appInitCacheInfo.get(DirectEquityConstants.DE_APPINIT_REQUEST_MAP_ENCODE_KEY))
            ) {
                encodedGenericRequestString = Base64.getEncoder().encodeToString(
                        objectMapper.writeValueAsString(request).getBytes());
            } else if (DirectEquityConstants.DE_APPINIT_REQUEST_MAP_ENCODE_RSA.equalsIgnoreCase(appInitCacheInfo.get(DirectEquityConstants.DE_APPINIT_REQUEST_MAP_ENCODE_KEY))) {
                encodedGenericRequestString = AsymmetricEncryptionUtil.encryptText(request.toString(), appInitCacheInfo.get(ENCRYPTION_PUBLIC_KEY));
            } else {
                encodedGenericRequestString = Base64.getEncoder().encodeToString(
                        objectMapper.writeValueAsString(request).getBytes());
            }
        } catch (JsonProcessingException ex) {
            throw DirectEquityExceptionFactory.syncTechnicalException(CustomerErrorCode.IECO_TECHNICAL,
                    DirectEquityConstants.DE_ORDER_PLACEMENT_FAILURE, ex);
        }
        return encodedGenericRequestString;
    }

    public Map<String, String> getAppInitInfoFromCache(String iecoUserId) {

        Map<String, String> requiredAppInitInfo = new HashMap<>();
        try{
            Object object = redisCacheClient.getObjectFromCache(iecoUserId);
            String respJson = objectMapper.writeValueAsString(object);
            DEAppinitResponseVO appInitCacheData = objectMapper.readValue(respJson, DEAppinitResponseVO.class);

            log.info("Inside if getAppInitInfoFromCache appInitCacheData {}", appInitCacheData);
            if(null != appInitCacheData) {
                requiredAppInitInfo.put(DirectEquityConstants.DE_APPINIT_REQUEST_MAP_ENCODE_KEY,appInitCacheData.getType());
                requiredAppInitInfo.put(DirectEquityConstants.DE_APPINIT_REQUEST_MAP_PUBLIC_KEY,appInitCacheData.getPublicKey());
                requiredAppInitInfo.put(DirectEquityConstants.DE_APPINIT_REQUEST_MAP_PUBLIC_KEY_SIZE,String.valueOf(appInitCacheData.getKeySize()));
                requiredAppInitInfo.put(DirectEquityConstants.DE_APPINIT_REQUEST_MAP_REDIRECT_HOST,appInitCacheData.getHost());
                requiredAppInitInfo.put(DirectEquityConstants.DE_APPINIT_REQUEST_MAP_REDIRECT_PORT,appInitCacheData.getPort());
                requiredAppInitInfo.put(DirectEquityConstants.DE_APPINIT_SESSION_ID,appInitCacheData.getSessionId());
                requiredAppInitInfo.put(DirectEquityConstants.DE_APPINIT_REQUEST_MAP_WEBLINK_HOST,appInitCacheData.getWeblinkhost());
                requiredAppInitInfo.put(DirectEquityConstants.DE_APPINIT_REQUEST_MAP_WEBLINK_PORT,appInitCacheData.getWeblinkport());
                requiredAppInitInfo.put(DirectEquityConstants.DE_APPINIT_REQUEST_MAP_SERVICE_HOST,appInitCacheData.getServicehost());
                requiredAppInitInfo.put(DirectEquityConstants.DE_APPINIT_REQUEST_MAP_SERVICE_PORT,appInitCacheData.getServiceport());
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return requiredAppInitInfo;
    }

}