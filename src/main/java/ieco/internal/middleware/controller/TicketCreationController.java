package ieco.internal.middleware.controller;

import ieco.internal.middleware.domain.request.*;
import ieco.internal.middleware.domain.response.AbstractResponse;
import ieco.internal.middleware.domain.response.CustomerDetailsResponse;
import ieco.internal.middleware.domain.response.ResponseObject;
import ieco.internal.middleware.domain.response.WhatsAppPushMessageResponse;
import ieco.internal.middleware.enums.ResponseCodeEnum;
import ieco.internal.middleware.feignclient.WhatsAppClient;
import ieco.internal.middleware.service.TicketCreationService;
import ieco.internal.middleware.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;

//@CrossOrigin(origins = {"https://iecouat.kotakcherry.com", "http://localhost:4200"})
@RestController
public class TicketCreationController {

    private Logger log = LoggerFactory.getLogger(TicketCreationController.class);
    @Autowired
    private TicketCreationService ticketCreationService;

    @Value("${isZohoEnabled}")
    private boolean isZohoEnable;

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

    @PostMapping("/zoho/tickets")
    public ResponseObject createLead(@RequestBody MiddlewareRequest req) {
        return ticketCreationService.ticketCreate(req);

    }

    @PostMapping("/zoho/tickets/callback")
    public ResponseObject createCallbackTicket(@Valid @RequestBody CallbackRequest req) {
        if (isZohoEnable) {
            return ticketCreationService.createCallbackZohoTicket(req);
        } else {
            return ResponseObject.builder().message("Zoho Ticket created successfully for callback").status("200 OK")
                    .responseCode("WC002").ticketId("987654").build();
        }

    }

    @PostMapping("/zoho/tickets/whatsAppChat")
    public ResponseObject createTicket(@Valid @RequestBody WhatsAppChatRequest req) {
        if (isZohoEnable) {
            return ticketCreationService.getZohoTicketDetails(req);
        } else {
            return ResponseObject.builder().message("Ticket created successfully").status("200 OK")
                    .responseCode("WC002").ticketId("987654").build();


        }

    }

    @PostMapping("/zoho/tickets/aadhardrop")
    public ResponseEntity<ResponseObject> createTicket(@RequestBody CleverTapRequestVO creq) {

        log.info("req from clever tap {}", creq);

        if (new NullCheck<>(creq).allNotNull(creq.getProfiles().get(0).getKeyValues()).isNotNull()) {

            String isSmsEnabled = creq.getProfiles().get(0).getKeyValues().getEnableSMS();
            String isWhatsAppEnabled = creq.getProfiles().get(0).getKeyValues().getEnableWhatsApp();
            if (isSmsEnabled.equalsIgnoreCase("Y") && isWhatsAppEnabled.equalsIgnoreCase("Y")) {
                sendSMS(creq);
                pushWhatsAppMessage(creq);
                ResponseObject res = new ResponseObject();
                res.setStatus("200 OK");
                return new ResponseEntity<>(res, HttpStatus.OK);
            } else {
                if (isSmsEnabled.equalsIgnoreCase("Y")) {
                    sendSMS(creq);
                    ResponseObject res = new ResponseObject();
                    res.setStatus("200 OK");
                    return new ResponseEntity<>(res, HttpStatus.OK);
                }
                if (isWhatsAppEnabled.equalsIgnoreCase("Y")) {
                    pushWhatsAppMessage(creq);
                    ResponseObject res = new ResponseObject();
                    res.setStatus("200 OK");
                    return new ResponseEntity<>(res, HttpStatus.OK);
                }
            }

            MiddlewareRequest req = new MiddlewareRequest();
            if (creq.getProfiles().get(0).getKeyValues().getCustomerId() != null) {
                if (creq.getProfiles().get(0).getKeyValues().getCustomerId().equals("-1")
                        || creq.getProfiles().get(0).getKeyValues().getCustomerId().equals("")) {
                    req.setCustomerId(null);
                } else {
                    req.setCustomerId(creq.getProfiles().get(0).getKeyValues().getCustomerId());
                }
            } else {
                req.setCustomerId(null);
            }
            req.setIssueDescription(creq.getProfiles().get(0).getKeyValues().getIssueDescription());
            req.setProductCategory(creq.getProfiles().get(0).getKeyValues().getProductCategory());
            req.setName(creq.getProfiles().get(0).getName());
            req.setEmail(creq.getProfiles().get(0).getEmail());
            req.setFromClevertap(true);

            return new ResponseEntity<>(ticketCreationService.ticketCreate(req), HttpStatus.OK);
        }
        return new ResponseEntity<>(AbstractResponse.responseError(
                CustomerConstants.EMAIL_MUST_NOT_BLANK, ResponseCodeEnum.BLANK_EMAIL), HttpStatus.OK);

    }

    private void sendSMS(CleverTapRequestVO cReq) {
        try {
            CustomerDetailsResponse custDetailsRes = customerUtil.getCustomerId(cReq);
            if (custDetailsRes.getStatus().equalsIgnoreCase("200 OK")) {
                String smsText = cReq.getProfiles().get(0).getKeyValues().getSmsMessage();
                smsText = this.returnCommonDynamicMessage(cReq, custDetailsRes, smsText);
                log.info("smsText - {}", smsText);
                String cReqMobile = cReq.getProfiles().get(0).getKeyValues().getMobile();
                String mobile = "";
                if (cReqMobile != null && !cReqMobile.isEmpty()) {
                    mobile = cReqMobile;
                } else {
                    mobile = custDetailsRes.getAttrs().getUserDetails().getMobile();
                }
                SMSRequestVO smsreq = SMSRequestVO.builder().priority("1").srcAppCd(sourceAppId)
                        .text(smsText).to(mobile).msgReqID(String.valueOf(System.currentTimeMillis()))
                        .contentTemplateId(cReq.getProfiles().get(0).getKeyValues().getSmsTemplate()) // 1107161552522878716
                        .principalId(principalId).build();
                smsTriggerUtil.sendSms(smsreq);
            } else {
                log.info("Customer details not found for provided email and customerId {} {}",
                        cReq.getProfiles().get(0).getEmail(),
                        cReq.getProfiles().get(0).getKeyValues().getCustomerId());
            }
        } catch (Exception e) {
            log.error("Exception in sendSMS - {0}. ", e);
        }
    }

    private void pushWhatsAppMessage(CleverTapRequestVO creq) {
        try {
            CustomerDetailsResponse custDetailsRes = customerUtil.getCustomerId(creq);
            if (custDetailsRes.getStatus().equalsIgnoreCase("200 OK")) {
                String cReqMobile = creq.getProfiles().get(0).getKeyValues().getMobile();
                String mobile = "";
                if (cReqMobile != null && !cReqMobile.isEmpty()) {
                    mobile = cReqMobile;
                } else {
                    mobile = custDetailsRes.getAttrs().getUserDetails().getMobile();
                }
                // Sandeep code for AES Encryption Start
                String optInDataToEncodeAndEncrypt = "method=" + optinMethod + "&format=" + optinFormat
                        + "&password=" + password + "&phone_number=" + mobile + "&v=" + v + "&auth_scheme="
                        + auth_scheme + "&channel=" + channel;
                String optInEncrdata = AESEncryption.encrypt(optInDataToEncodeAndEncrypt);
                optinEncrp(optInEncrdata);
                String whatsAppSms = creq.getProfiles().get(0).getKeyValues().getWhatsappsms();
                whatsAppSms = this.encodeValueForDynaicTxt(whatsAppSms);
                whatsAppSms = this.returnCommonDynamicMessage(creq, custDetailsRes, whatsAppSms);
                log.info("whatsAppSms - {}", whatsAppSms);
                String dataToEncodeAndEncrypt = "method=" + method + "&format=" + format + "&password="
                        + password + "&send_to=" + mobile + "&v=" + v + "&auth_scheme=" + auth_scheme
                        + "&msg_type=" + msg_type + "&msg=" + whatsAppSms;
                boolean isTemplate = creq.getProfiles().get(0).getKeyValues().isTemplate();
                if(isTemplate){
                    dataToEncodeAndEncrypt = dataToEncodeAndEncrypt + "&isTemplate=true";
                }

                String encrdata = AESEncryption.encrypt(dataToEncodeAndEncrypt);
                log.info("whatsAppPushMessage encrdata {}", encrdata);
                WhatsAppPushMessageResponse whatsAppPushMessageResponse =
                        this.pushEncryptedMessage(encrdata);
                log.info("whatsAppPushMessageResponse res {}", whatsAppPushMessageResponse);
                // Sandeep code for AES Encryption End
            } else {
                log.info("Customer details not found for provided email and customerId {} {}",
                        creq.getProfiles().get(0).getEmail(),
                        creq.getProfiles().get(0).getKeyValues().getCustomerId());
            }
        } catch (Exception e) {
            log.error("Exception in pushWhatsAppMessage - {0}. ", e);
        }
    }

    private String returnCommonDynamicMessage(CleverTapRequestVO cReq,
                                              CustomerDetailsResponse custDetailsRes, String message) {
        String messageTxt = "";
        try {
            String[] paramsArr = cReq.getProfiles().get(0).getKeyValues().getParams().split(",");
            messageTxt = message;
            LinkedList<String> list = new LinkedList<>();
            for (String param : paramsArr) {
                param = param.trim();
                if (param.equalsIgnoreCase("name")) {
                    String cReqMobile = cReq.getProfiles().get(0).getKeyValues().getMobile();
                    if (cReqMobile != null && !cReqMobile.isEmpty()) {
                        list.add("savvy");
                    } else {
                        list.add(custDetailsRes.getAttrs().getUserDetails().getFname());
                    }
                }
                if (param.equalsIgnoreCase("url")) {
                    list.add(cReq.getProfiles().get(0).getKeyValues().getUrl());
                }
            }
            messageTxt = java.text.MessageFormat.format(messageTxt, list.toArray()); // replaces
            // messageTxt {0} and
            // {1} value
            // dynamically
            return messageTxt;
        } catch (Exception e) {
            log.error("Exception in returnCommonMessage method - {0}.", e);
        }
        return messageTxt;
    }

    private String encodeValueForDynaicTxt(String value) {
        String result;
        try {
            result = value.replaceAll("%7B", "{").replaceAll("%7D", "}");
        } catch (Exception e) {
            log.info("error in encoding encodeValue {0}.", e);
            result = value;
        }
        return result;
    }



    private WhatsAppPushMessageResponse pushEncryptedMessage(String encrdata) {
        try {
            WhatsAppPushMessageResponse whatsAppPushMessageResponse =
                    whatsAppClient.pushEncryptedMessage(userid, encrdata);
            return whatsAppPushMessageResponse;
        } catch (Exception exception) {
            log.error("Exception in pushEncryptedMessage - {0}. ", exception);
        }
        return null;
    }

    void optinEncrp(String encrdata) {
        try {
            whatsAppClient.optinEncrp(userid, encrdata);
        } catch (Exception e) {
            log.error("Exception in optinEncrp - {0}. ", e);
        }
    }

    void optin(String mobile) {
        whatsAppClient.optIn(optinMethod, format, userid, password, mobile, v, auth_scheme, channel);
    }

    // sendwhatsapptext
    // sendwhatsappmedia

    @PostMapping("/sendWhatsAppMedia")
    public ResponseEntity<String> sendMedia(@RequestBody @Valid WhatsAppMediaRequest creq) {
        log.info("intializing sendWhatsAppMedia request {}", creq);
        try {
            CustomerDetailsResponse custDetailsRes = customerUtil.getCustomerId(creq);
            String cReqMobile = creq.getProfiles().get(0).getKeyValues().getMobile();
            String mobile = "";
            if (cReqMobile != null && !cReqMobile.isEmpty()) {
                mobile = cReqMobile;
            } else {

                mobile = custDetailsRes.getAttrs().getUserDetails().getMobile();

            }
            String whatsAppSms = creq.getProfiles().get(0).getKeyValues().getWhatsAppSms();
            whatsAppSms = this.encodeValueForDynaicTxt(whatsAppSms);
            whatsAppSms = this.returnDynamicMessage(creq, custDetailsRes, whatsAppSms);
            log.info("whatsAppSms - {}", whatsAppSms);


            String msg = "method=SendMediaMessage&format=json&password=" + password + "&send_to=" + mobile
                    + "&v=" + v + "&auth_scheme=" + auth_scheme + "&isTemplate=true"
                    + "&msg_type=IMAGE&media_url=" + creq.getProfiles().get(0).getKeyValues().getMediaURL()
                    + "&caption=" + whatsAppSms + "&footer="
                    + creq.getProfiles().get(0).getKeyValues().getFooter();
            log.info("messageValue {0}.", msg); //NOSONAR
            String enc = AESEncryption.encrypt(msg);
            log.info("encryptValue of messages {0}.", enc); //NOSONAR
            WhatsAppPushMessageResponse whatsAppPushMessageResponse = this.pushEncryptedMessage(enc);
            log.info("whatsAppPushMessageResponse res {}", whatsAppPushMessageResponse);
        } catch (Exception e) {
            log.error("Exception in sendMedia - {0}.", e);
            e.printStackTrace();
        }
        return new ResponseEntity<>("success", HttpStatus.OK);

    }


    private String returnDynamicMessage(WhatsAppMediaRequest cReq,
                                        CustomerDetailsResponse custDetailsRes, String message) {
        String messageTxt = "";
        try {
            String[] paramsArr = cReq.getProfiles().get(0).getKeyValues().getParams().split(",");
            messageTxt = message;
            LinkedList<String> list = new LinkedList<>();
            for (String param : paramsArr) {
                param = param.trim();
                if (param.equalsIgnoreCase("name")) {
                    String cReqMobile = cReq.getProfiles().get(0).getKeyValues().getMobile();
                    if (cReqMobile != null && !cReqMobile.isEmpty()) {
                        list.add("savvy");
                    } else {
                        list.add(custDetailsRes.getAttrs().getUserDetails().getFname());
                    }
                }
                if (param.equalsIgnoreCase("url")) {
                    list.add(cReq.getProfiles().get(0).getKeyValues().getUrl());
                }
            }
            messageTxt = java.text.MessageFormat.format(messageTxt, list.toArray()); // replaces
            // messageTxt {0} and
            // {1} value
            // dynamically
            return messageTxt;
        } catch (Exception e) {
            log.error("Exception in returnCommonMessage method - {0}.", e);
        }
        return messageTxt;
    }
}