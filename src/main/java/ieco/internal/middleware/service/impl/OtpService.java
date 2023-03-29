package ieco.internal.middleware.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ieco.internal.middleware.domain.request.*;
import ieco.internal.middleware.domain.response.*;
import ieco.internal.middleware.feignclient.PwcCustomerClient;
import ieco.internal.middleware.feignclient.PwcOtpClient;
import ieco.internal.middleware.feignclient.RedisCacheClient;
import ieco.internal.middleware.feignclient.ZohoClient;
import ieco.internal.middleware.util.CustomerConstants;
import ieco.internal.middleware.util.NullCheck;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class OtpService {

    private static final String DEFAULT_DEPARTMENT = "KIAL";
    public static final String OK = "200 OK";
    private org.slf4j.Logger log = LoggerFactory.getLogger(OtpService.class);

    /**
     * The cache service impl.
     */
    @RestClient
    private PwcOtpClient otpClient;


    @RestClient
    private ZohoClient zohoClient;

    @RestClient
    private PwcCustomerClient iecoCustomerClient;

    @RestClient
    private RedisCacheClient redisCacheClient;
    @Autowired
    private ObjectMapper objectMapper;

    @Value("${contactCreation}")
    private String contactCreationURL;

    @Value("${iecoToken}")
    private String iecoToken;

    @Value("${searchContact}")
    private String searchContactURL;

    @Value("${ticketCreation}")
    private String ticketCreationURL;

    @Value("${otpValidate}")
    private String otpValidateURL;

    @Value("${otpGenerate}")
    private String otpGenerateURL;

    @Value("${otpResend}")
    private String otpResendURL;

    @Value("${initiateTransaction}")
    private String initiateTransactionURL;

    @Value("${customerDetails}")
    private String customerDetailsURL;

    @Value("${orgId}")
    private String orgid;

    @Value("${zohoenv}")
    private String environment;


    public ResponseEntity<ResponseObject> initiateTransaction(InitiateTransactionRequest req) {
        log.info("InitiateTransactionRequest received from bot {}", req);
        ResponseEntity<ResponseObject> resEntity = iecoCustomerClient.initiateTransaction(req);
        String tempToken = (String) redisCacheClient.getObjectFromCache(CustomerConstants.TEMP_CUST_ID_SESSION_CACHE_MAP, req.getSessionId());
        log.info("tempToken into otpGenerate method  {}", tempToken);
        if (new NullCheck<>(tempToken).isNull() || (new NullCheck<>(tempToken).isNotNull() && (req.getTempCustomerId() != (Integer.valueOf(tempToken)).intValue()))) {
            ResponseObject responseObject = new ResponseObject();
            responseObject.setTimeStamp(System.currentTimeMillis());
            responseObject.setStatus(HttpStatus.UNAUTHORIZED.toString());
            responseObject.setMessage(CustomerConstants.UNAUTHORIZED);
            return new ResponseEntity<>(responseObject, HttpStatus.UNAUTHORIZED);
        }
        log.info("InitiateTransactionRespone received from PwC {}", resEntity.getBody());
        return resEntity;

    }

    public ResponseEntity<ResponseObject> otpGenerate(OtpRequest req) {
        log.info("otpGenerate Request received from bot {}", req);
        ResponseEntity<ResponseObject> resEntity = otpClient.otpGenerate(req);
        String tempToken = (String) redisCacheClient.getObjectFromCache(CustomerConstants.TEMP_CUST_ID_SESSION_CACHE_MAP, req.getSessionLogId());
        log.info("tempToken into otpGenerate method  {}", tempToken);
        if (new NullCheck<>(tempToken).isNull() || (new NullCheck<>(tempToken).isNotNull() && (req.getCustomerId() != (Integer.valueOf(tempToken)).intValue()))) {
            ResponseObject responseObject = new ResponseObject();
            responseObject.setTimeStamp(System.currentTimeMillis());
            responseObject.setStatus(HttpStatus.UNAUTHORIZED.toString());
            responseObject.setMessage(CustomerConstants.UNAUTHORIZED);
            return new ResponseEntity<>(responseObject, HttpStatus.UNAUTHORIZED);
        }
        log.info("otpGenerate Respone received from PwC {}", resEntity.getBody());
        return resEntity;
    }

    public ResponseEntity<ResponseObject> otpResend(OtpRequest req) {
        log.info("otpResend Request received from bot {}", req);
        ResponseEntity<ResponseObject> resEntity = otpClient.otpResend(req);
        log.info("otpResend Respone received from PwC {}", resEntity.getBody());
        return resEntity;

    }

    ResponseEntity<ResponseObject> verifyOtp(MiddlewareRequest middlewareRequest) {

        OtpRequest otpReq = OtpRequest.builder().communicationTypeId(3)
                .customerId(middlewareRequest.getTempCustomerId()).emailId(middlewareRequest.getEmail())
                .mobileNumber(middlewareRequest.getMobileNumber()).otpNumber(middlewareRequest.getOtp())
                .sessionLogId(middlewareRequest.getSessionId())
                .transactionId(middlewareRequest.getTransactionId()).build();
        log.info("verifyOtp Request {}", otpReq);
        ResponseEntity<ResponseObject> otpValidateResEntity = otpClient.otpVerify(otpReq);
        log.info("otpValidation response from PwC {}", otpValidateResEntity.getBody());
        return otpValidateResEntity;
    }

    public CustomerDetailsResponse getCustomerdetails(int custId, String email) {

        CustomerDetailsRequest customerDetailsReq = new CustomerDetailsRequest();
        if (email != null && !email.isEmpty()) {
            log.info("Getting customer details for the email:{} ", email);

            customerDetailsReq.setCustomerEmail(email);
        } else {
            customerDetailsReq.setCustomerId(custId);
        }

        log.info("getCustomerdetails Request {}", customerDetailsReq);

        ResponseEntity<CustomerDetailsResponse> custDetailsRes =
                iecoCustomerClient.getCustomerDetails(customerDetailsReq);
        log.info("getCustomerdetails Response from PwC {}", custDetailsRes.getBody());
        return custDetailsRes.getBody();

    }

    public TicketCreationResponse createTicket(MiddlewareRequest middlewareRequest, String contactId,
                                               String IecoId) {

        log.info("In create tickect");
        if (!middlewareRequest.isSatisfied()) {

            TicketCreationRequest tcRequest = new TicketCreationRequest();
            DeskContactCreationCustomFields cf = new DeskContactCreationCustomFields();


            cf.setCf_environment(environment);

            cf.setCf_sr_nature("FTR");
            tcRequest.setPriority("Medium");

            cf.setCf_creation_mode("Automatic");
            cf.setCf_kotak_360(IecoId);
            cf.setCf_product_type(middlewareRequest.getProductCategory());


            cf.setCf_ticket_department(DEFAULT_DEPARTMENT);
            cf.setCf_created_date(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            tcRequest.setCf(cf);
            tcRequest.setSubject("Ticket has been created");
            tcRequest.setSubCategory(
                    middlewareRequest.getSubCategory() != null ? middlewareRequest.getSubCategory() : "");
            tcRequest.setDepartmentId("17634000000072420");
            tcRequest.setContactId(contactId);
            tcRequest.setDescription(middlewareRequest.getIssueDescription());
            tcRequest.setChannel("Chatbot");
            TicketCreationResponse responseEntity = zohoClient.ticketCreate(tcRequest);
            log.info("Ticket created successfully res {}", responseEntity);
            responseEntity.setStatus(OK);
            return responseEntity;
        }

        return TicketCreationResponse.builder().status(OK)
                .message("User has been satisfied with bot reply").build();

    }

    @SuppressWarnings("unchecked")
    public <T> T processRequest(MiddlewareRequest middlewareRequest)
            throws JsonMappingException, JsonProcessingException {
        log.info("middleware request from bot {}", middlewareRequest);


        ResponseEntity<ResponseObject> otpValidateResEntity = verifyOtp(middlewareRequest);

        ResponseObject otpValidateRes;
        if (otpValidateResEntity.getStatusCodeValue() == 200) {
            otpValidateRes = otpValidateResEntity.getBody();

            if (otpValidateRes.getStatus().equalsIgnoreCase("200 ok")
                    && otpValidateRes.getMessage().equalsIgnoreCase("OTP validate successfully")) {// otp
                // verification
                return createTicket(middlewareRequest);


            } else {
                return (T) otpValidateResEntity;
            }
        } // close otp verify if
        else {
            return (T) otpValidateResEntity;
        }

    }

    public <T> T createTicket(MiddlewareRequest middlewareRequest) {
        ZohoContactCreationResponse contactCreationRes;

        CustomerDetailsResponse custRes =
                getCustomerdetails(middlewareRequest.getTempCustomerId(), middlewareRequest.getEmail());
        if (custRes.getStatus().equalsIgnoreCase(OK)) { // check customer or not


            log.info("Customer Found in IECO platform");
            ContactDetailsResponse searchContactRes;
            ResponseEntity<ContactDetailsResponse> searchContactResentity =
                    zohoClient.searchContact("cf_kotak_360" + ":"
                            + Long.toString(custRes.getAttrs().getUserDetails().getCustomerId()));
            if (searchContactResentity.getStatusCodeValue() == 200) {
                searchContactRes = searchContactResentity.getBody();
                log.info("Search contact res {}", searchContactRes);
                String contactId = searchContactRes.getContactResponse().get(0).getId();
                String iecoId = Long.toString(custRes.getAttrs().getUserDetails().getCustomerId());
                return (T) createTicket(middlewareRequest, contactId, iecoId);
            }
        } // customer details if

        else {
            log.info("Customer not existed in IECO platform");

            ResponseEntity<ContactDetailsResponse> searchContactResEntity =
                    zohoClient.searchContact("email" + ":" + middlewareRequest.getEmail());
            if (searchContactResEntity.getStatusCodeValue() == 200) {
                return (T) createTicket(middlewareRequest,
                        searchContactResEntity.getBody().getContactResponse().get(0).getId(),
                        searchContactResEntity.getBody().getContactResponse().get(0).getCf().getCf_kotak_360());

            } else {
                log.info("creating lead in zoho");
                contactCreationRes = zohoClient
                        .contactCreate(formContactCreationRequest(custRes, middlewareRequest, "lead"));
                log.info("creating lead in zoho res {}", contactCreationRes);
                return (T) createTicket(middlewareRequest, contactCreationRes.getId(), "1234");
            }
        }

        return null;
    }

    DeskContactCreationRequest formContactCreationRequest(CustomerDetailsResponse custRes,
                                                          MiddlewareRequest middlewareRequest, String custType) {

        DeskContactCreationRequest contactCreationReq = new DeskContactCreationRequest();
        DeskContactCreationCustomFields cf = new DeskContactCreationCustomFields();
        cf.setCf_environment(environment);
        if (custType.equalsIgnoreCase("contact")) {
            cf.setCf_kotak_360(Long.toString(custRes.getAttrs().getUserDetails().getCustomerId()));
            contactCreationReq.setEmail(custRes.getAttrs().getUserDetails().getEmail());
            contactCreationReq.setLastName(custRes.getAttrs().getUserDetails().getLname());
            contactCreationReq.setType(custType);
            contactCreationReq.setCf(cf);
        } else {
            cf.setCf_kotak_360("1234");
            if (middlewareRequest.getName().contains(" ")) {
                contactCreationReq.setFirstName(middlewareRequest.getName().split(" ")[0]);
                contactCreationReq.setLastName(middlewareRequest.getName().split(" ")[1]);
            } else {
                contactCreationReq.setFirstName(middlewareRequest.getName());
                contactCreationReq.setLastName("NA");
            }

            contactCreationReq.setEmail(middlewareRequest.getEmail());
            contactCreationReq.setMobile(middlewareRequest.getMobileNumber());
            contactCreationReq.setType(custType);
            contactCreationReq.setCf(cf);
        }

        return contactCreationReq;

    }

    public ZohoAttachmentResponse uploadFile(FileUpload file, String id) {
        log.info("zoho attachment file name {}", file.fileName());
        ZohoAttachmentResponse attachmentRes = zohoClient.ticketAttachment(file, id);
        log.info("zoho attachment api response {}", attachmentRes);
        attachmentRes.setStatus(OK);
        return attachmentRes;
    }
}