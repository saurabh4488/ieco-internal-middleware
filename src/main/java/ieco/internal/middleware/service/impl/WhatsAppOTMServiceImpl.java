package ieco.internal.middleware.service.impl;

import ieco.internal.middleware.domain.request.CustomerDetailsRequest;
import ieco.internal.middleware.domain.request.WhatsAppOTMRequest;
import ieco.internal.middleware.domain.response.AbstractResponse;
import ieco.internal.middleware.domain.response.CustomerDetailsResponse;
import ieco.internal.middleware.domain.response.ResponseObject;
import ieco.internal.middleware.domain.response.WhatsAppPushMessageResponse;
import ieco.internal.middleware.enums.ResponseCodeEnum;
import ieco.internal.middleware.feignclient.PwcCustomerClient;
import ieco.internal.middleware.feignclient.WhatsAppClient;
import ieco.internal.middleware.service.WhatsAppOTMService;
import ieco.internal.middleware.util.AESEncryption;
import ieco.internal.middleware.util.CustomerUtil;
import ieco.internal.middleware.util.NullCheck;
import ieco.internal.middleware.util.SMSTriggerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
@Slf4j
public class WhatsAppOTMServiceImpl extends AbstractResponse implements WhatsAppOTMService {

    @Autowired
    private PwcCustomerClient pwcCustomerClient;

    @Autowired
    private CustomerUtil customerUtil;

    @Autowired
    private WhatsAppClient whatsAppClient;

    @Autowired
    private SMSTriggerUtil smsTriggerUtil;

    @Value("${whatsapp.method}")
    private String method;

    @Value("${whatsapp.format}")
    private String format;

    @Value("${whatsapp.userid}")
    private String userid;

    @Value("${whatsapp.password}")
    private String password;

    @Value("${whatsapp.v}")
    private String v;

    @Value("${whatsapp.auth_scheme}")
    private String auth_scheme;

    @Value("${whatsapp.msg_type}")
    private String msg_type;

    @Value("${whatsapp.optin.method}")
    private String optinMethod;

    @Value("${whatsapp.optin.format}")
    private String optinFormat;

    @Value("${whatsapp.channel}")
    private String channel;

    @Value("${sms.sourceappid}")
    private String sourceAppId;

    @Value("${sms.principalId}")
    private String principalId;

    @Value("${whatsapp.otm.message}")
    private String whatsappOTMMessage;

    @Value("${whatsapp.otm.deepLink}")
    private String whatsappOTMDeepLink;

    @Value("${whatsapp.otm.mediaUrl}")
    private String whatsappOTMMediaUrl;

    @Value("${whatsapp.otm.isTemplate}")
    private boolean whatsappOTMIsTemplate;


    @Override
    public ResponseObject sendWhatsAppOTMMessage(WhatsAppOTMRequest whatsAppOTMRequest) {
        try {
            CustomerDetailsResponse customerDetailsResponse = getCustomerId(whatsAppOTMRequest.getCustomerId());
            if (customerDetailsResponse.getStatus().equalsIgnoreCase("200 OK")) {
                String mobile = whatsAppOTMRequest.getMobile();
                String customerName = customerDetailsResponse.getAttrs().getUserDetails().getFname();
                if (!new NullCheck<>(mobile).isNotNullOrEmpty()) {
                    mobile = customerDetailsResponse.getAttrs().getUserDetails().getMobile();
                }

                whatsAppOTMRequest.setCustomerName(customerName);
                whatsAppOTMRequest.setMobile(mobile);
                whatsAppOTMRequest.setWhatsAppMessage(whatsappOTMMessage);
                whatsAppOTMRequest.setDeepLink(whatsappOTMDeepLink);
                whatsAppOTMRequest.setMediaUrl(whatsappOTMMediaUrl);
                whatsAppOTMRequest.setTemplate(whatsappOTMIsTemplate);

                log.info("whatsAppOTMRequest == {}", whatsAppOTMRequest);

                String optInDataToEncodeAndEncrypt = "method=" + optinMethod + "&format=" + optinFormat
                        + "&password=" + password + "&phone_number=" + mobile + "&v=" + v + "&auth_scheme="
                        + auth_scheme + "&channel=" + channel;
                String optInEncrdata = AESEncryption.encrypt(optInDataToEncodeAndEncrypt);
                optinEncrp(optInEncrdata);
                String whatsAppSms = whatsAppOTMRequest.getWhatsAppMessage();
                whatsAppSms = this.encodeValueForDynaicTxt(whatsAppSms);
                whatsAppSms = this.returnCommonDynamicMessage(whatsAppOTMRequest, whatsAppSms);
                log.info("whatsAppSms - {}", whatsAppSms);

                String dataToEncodeAndEncrypt = "method=SendMediaMessage&format=json&password=" + password + "&send_to=" + mobile
                        + "&v=" + v + "&auth_scheme=" + auth_scheme + "&isTemplate=" + whatsAppOTMRequest.isTemplate()
                        + "&msg_type=IMAGE&media_url=" + whatsAppOTMRequest.getMediaUrl()
                        + "&caption=" + whatsAppSms;

                log.info("dataToEncodeAndEncrypt == {}", dataToEncodeAndEncrypt);

                String encrdata = AESEncryption.encrypt(dataToEncodeAndEncrypt);
                log.info("whatsAppPushMessage encrdata {}", encrdata);
                WhatsAppPushMessageResponse whatsAppPushMessageResponse = this.pushEncryptedMessage(encrdata);
                log.info("whatsAppPushMessageResponse res {}", whatsAppPushMessageResponse);
                if (whatsAppPushMessageResponse.getResponse().status.equalsIgnoreCase("success")) {
                    ResponseObject responseObject = responseSuccess("Success", ResponseCodeEnum.WHATSAPP_OTM_SUCCESS);
                    return responseObject;
                }
            } else {
                log.info("Customer details not found for provided email and customerId {} {}",
                        whatsAppOTMRequest.getCustomerId());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        ResponseObject responseObject = responseError("Error", ResponseCodeEnum.WHATSAPP_OTM_FAILURE);
        return responseObject;
    }

    public CustomerDetailsResponse getCustomerId(String customerId) {
        CustomerDetailsRequest customerDetailsReq = new CustomerDetailsRequest();
        customerDetailsReq.setCustomerId(Integer.parseInt(customerId));
        ResponseEntity<CustomerDetailsResponse> custDetailsResEntity = pwcCustomerClient.getCustomerDetails(customerDetailsReq);
        CustomerDetailsResponse customerDetailsResponse = custDetailsResEntity.getBody();
        log.info("CustomerDetailsResponse from PwC {}", customerDetailsResponse);
        return customerDetailsResponse;
    }

    void optinEncrp(String encrdata) {
        try {
            whatsAppClient.optinEncrp(userid, encrdata);
        } catch (Exception e) {
            log.error("Exception in optinEncrp - {} ", e);
        }
    }

    private String encodeValueForDynaicTxt(String value) {
        String result;
        try {
            result = value.replace("%7B", "{").replace("%7D", "}");
        } catch (Exception e) {
            log.info("error in encoding encodeValue {}", e);
            result = value;
        }
        return result;
    }

    private String returnCommonDynamicMessage(WhatsAppOTMRequest whatsAppOTMRequest, String message) {
        try {
            LinkedList<Object> list = new LinkedList<>();
            list.add(whatsAppOTMRequest.getCustomerName());
            list.add(whatsAppOTMRequest.getSipAmount());
            list.add(whatsAppOTMRequest.getSchemeName());
            list.add(whatsAppOTMRequest.getDeepLink());
            log.info("list == {}", list);
            message = java.text.MessageFormat.format(message, list.toArray()); // replaces
            return message;
        } catch (Exception e) {
            log.error("Exception in returnCommonMessage method - {}", e);
        }
        return message;
    }

    private WhatsAppPushMessageResponse pushEncryptedMessage(String encrdata) {
        try {
            WhatsAppPushMessageResponse whatsAppPushMessageResponse = whatsAppClient.pushEncryptedMessage(userid, encrdata);
            return whatsAppPushMessageResponse;
        } catch (Exception exception) {
            log.error("Exception in pushEncryptedMessage - {} ", exception);
        }
        return null;
    }
}
