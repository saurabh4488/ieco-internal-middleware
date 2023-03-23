package ieco.internal.middleware.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import ieco.internal.middleware.domain.request.*;
import ieco.internal.middleware.domain.request.sms.SMSAPIReq;
import ieco.internal.middleware.domain.request.sms.SMSAPIReq.Header;
import ieco.internal.middleware.domain.response.*;
import ieco.internal.middleware.enums.ResponseCodeEnum;
import ieco.internal.middleware.exception.IecoRuntimeException;
import ieco.internal.middleware.feignclient.GWAccessTokenClient;
import ieco.internal.middleware.feignclient.SMSClient;
import ieco.internal.middleware.model.AdminDetails;
import ieco.internal.middleware.model.WaitingListDetailsEntity;
import ieco.internal.middleware.model.WaitingListSettingsEntity;
import ieco.internal.middleware.repository.AdminDetailsRepository;
import ieco.internal.middleware.repository.WaitingListRepository;
import ieco.internal.middleware.repository.WaitingListSettingsRepository;
import ieco.internal.middleware.service.WaitingListService;
import ieco.internal.middleware.util.NullCheck;
import ieco.internal.middleware.util.RestUtility;
import ieco.internal.middleware.xml.transformation.XMLTransformer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.StringWriter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Pattern;

@Service
public class WaitingListServiceImpl extends AbstractResponse implements WaitingListService {

    public static final String USER_DETAILS = "userDetails";
    public static final String ERROR_WITH_CHECK_EMAIL_S = "%s [ERROR] with [checkEmail]: %s";
    public static final String REFCODE = "?refcode=";
    public static final String PRINCIPAL_ID = "1201160455792349833";
    public static final String DETAILS_UPDATED_SUCCESSFULLY = "Details Updated successfully";
    public static final String PROVIDE_VALID_TOKEN = "Please provide valid token!";
    public static final String AUTHENTICATION_IS_REQUIRED_TO_ACCESS_THIS_RESOURCE = "Authentication is required to access this resource!";
    public static final String YOU_ARE_A_STAR_YOU_HAVE_CROSSED_A_MILESTONE_OF = "You are a star! You have crossed a milestone of ";
    public static final String WANNA_MAKE_YOUR_WAY_UP_HERE_IS_HOW = " . Wanna make your way UP! Here is how ";
    public static final String KOTAK_CHERRY = "  - Kotak Cherry";
    /**
     * The EMAIL from.
     */
    @Value("${notificationRequest.email.from}")
    public String emailFrom;
    /**
     * The EMAIL from.
     */
    @Value("${notificationRequest.email.subject}")
    public String emailSubject;
    @Value("${notificationRequest.email.uniqueLink}")
    public String appURL;
    @Value("${cherry.url}")
    public String cherryURL;
    @Autowired
    public RestUtility restUtility;
    /**
     * The log.
     */
    private Logger log = LoggerFactory.getLogger(WaitingListServiceImpl.class);
    private Pattern patternToCheckInteger = Pattern.compile("^\\d+$");
    @Autowired
    private WaitingListRepository waitingListRepository;
    @Autowired
    private AdminDetailsRepository adminDetailsRepository;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private WaitingListSettingsRepository waitingListSettingsRepository;
    @Autowired
    private VelocityEngine velocityEngine;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private XMLTransformer transformer;
    @Autowired
    private SMSClient gatewayClient;
    @Autowired
    private GWAccessTokenClient tokenClient;
    @Value("${sms.smsClientId}")
    private String clientId;
    @Value("${sms.smsClientSecret}")
    private String clientSecret;
    @Value("${finacle.grantType}")
    private String grantType;
    @Value("${sms.sourceappid}")
    private String sourceAppId;
    @Value("${sms.from}")
    private String from;

    @Override
    public ResponseObject createWaitingListNumber(IncomingEmail email) {

        try {
            WaitingListDetailsEntity customerDetailsEntity = waitingListRepository
                    .findByEmailIdIgnoreCase(email.getEmail());
            if (customerDetailsEntity != null) {

                WaitingListDetailsVO response = new WaitingListDetailsVO();
                if (customerDetailsEntity.getIsFirstTime().equalsIgnoreCase("Y")) {
                    customerDetailsEntity.setIsFirstTime("N");
                    waitingListRepository.save(customerDetailsEntity);
                    if (customerDetailsEntity.getFullName() == null
                            || customerDetailsEntity.getFullName().trim().isEmpty()) {
                        response.setFullName("");

                    }
                    if (customerDetailsEntity.getMobileNumber() == null
                            || customerDetailsEntity.getMobileNumber().trim().isEmpty()) {
                        response.setMobileNumber("");
                    }
                    if (customerDetailsEntity.getLinkedinURL() == null
                            || customerDetailsEntity.getLinkedinURL().trim().isEmpty()) {
                        response.setLinkedinURL("");
                    }
                    if (customerDetailsEntity.getCheck1() == null
                            || customerDetailsEntity.getCheck1().trim().isEmpty()) {
                        response.setCheck1("");
                    }
                    if (customerDetailsEntity.getCheck2() == null
                            || customerDetailsEntity.getCheck2().trim().isEmpty()) {
                        response.setCheck2("");
                    }
                    response.setEmailId(customerDetailsEntity.getEmailId());
                    response.setWaitingListNumber(customerDetailsEntity.getWaitingListNumber());
                    response.setReferenceCode(customerDetailsEntity.getReferenceCode());
                    response.setOffSet(customerDetailsEntity.getOffset());
                    response.setIsAccessProvided(customerDetailsEntity.getIsAccessProvided());
                    response.setFirstTime(false);
                    response.setSource(customerDetailsEntity.getSource());
                    response.setMedium(customerDetailsEntity.getMedium());
                    response.setExtras(customerDetailsEntity.getExtras());
                    log.info("Response for check status {}", response);
                    return responseSuccess("Email Already exists!", ResponseCodeEnum.EMAIL_EXISTS, response,
                            USER_DETAILS);
                }

                if (customerDetailsEntity.getFullName() == null
                        || customerDetailsEntity.getFullName().trim().isEmpty()) {
                    response.setFullName("");

                }
                if (customerDetailsEntity.getMobileNumber() == null
                        || customerDetailsEntity.getMobileNumber().trim().isEmpty()) {
                    response.setMobileNumber("");
                }
                if (customerDetailsEntity.getLinkedinURL() == null
                        || customerDetailsEntity.getLinkedinURL().trim().isEmpty()) {
                    response.setLinkedinURL("");
                }
                if (customerDetailsEntity.getCheck1() == null || customerDetailsEntity.getCheck1().trim().isEmpty()) {
                    response.setCheck1("");
                }
                if (customerDetailsEntity.getCheck2() == null || customerDetailsEntity.getCheck2().trim().isEmpty()) {
                    response.setCheck2("");
                }
                response.setEmailId(customerDetailsEntity.getEmailId());
                response.setWaitingListNumber(customerDetailsEntity.getWaitingListNumber());
                response.setReferenceCode(customerDetailsEntity.getReferenceCode());
                response.setOffSet(customerDetailsEntity.getOffset());
                response.setIsAccessProvided(customerDetailsEntity.getIsAccessProvided());
                response.setFirstTime(false);
                response.setSource(customerDetailsEntity.getSource());
                response.setMedium(customerDetailsEntity.getMedium());
                response.setExtras(customerDetailsEntity.getExtras());
                log.info("Response for check status {}", response);
                return responseSuccess("Email Already exists!", ResponseCodeEnum.EMAIL_EXISTS, response, USER_DETAILS);

            }
            return createWaitingNumber(email);
        } catch (Exception e) {
            throw new IecoRuntimeException(String.format(ERROR_WITH_CHECK_EMAIL_S, e.getMessage(), email), e);
        }
    }

    private ResponseObject createWaitingNumber(IncomingEmail request) throws NoSuchAlgorithmException {
        WaitingListDetailsVO response = new WaitingListDetailsVO();
        String first3Characters = request.getEmail().substring(0, Math.min(request.getEmail().length(), 4));

        String refCode = first3Characters + generateRandomNumber(4);
        Integer waitingListNumber = waitingListSettingsRepository.getCurrentWaitingId();

        WaitingListDetailsEntity customerDetailsEntity = WaitingListDetailsEntity.builder().emailId(request.getEmail())
                .referenceCode(refCode).isAccessProvided("N").createdTime(new Date()).isFirstTime("Y").offset(0)
                .waitingListNumber(waitingListNumber).referedCount(0).source(request.getSource())
                .medium(request.getMedium()).extras(request.getExtras()).updatedTime(new Date())
                .invitationURL(appURL + REFCODE + refCode).build();

        WaitingListDetailsEntity savedDetails = waitingListRepository.save(customerDetailsEntity);

        if (savedDetails.getFullName() == null || savedDetails.getFullName().trim().isEmpty()) {
            response.setFullName("");

        }
        if (savedDetails.getMobileNumber() == null || savedDetails.getMobileNumber().trim().isEmpty()) {
            response.setMobileNumber("");
        }
        if (savedDetails.getLinkedinURL() == null || savedDetails.getLinkedinURL().trim().isEmpty()) {
            response.setLinkedinURL("");
        }
        if (savedDetails.getCheck1() == null || savedDetails.getCheck1().trim().isEmpty()) {
            response.setCheck1("");
        }
        if (savedDetails.getCheck2() == null || savedDetails.getCheck2().trim().isEmpty()) {
            response.setCheck2("");
        }
        response.setEmailId(savedDetails.getEmailId());
        response.setWaitingListNumber(savedDetails.getWaitingListNumber());
        response.setReferenceCode(savedDetails.getReferenceCode());
        response.setOffSet(savedDetails.getOffset());
        response.setIsAccessProvided(savedDetails.getIsAccessProvided());
        response.setFirstTime(true);
        response.setSource(savedDetails.getSource());
        response.setMedium(savedDetails.getMedium());
        response.setExtras(savedDetails.getExtras());

        CompletableFuture.runAsync(() -> {
            try {
                log.info("Sending mail started for the user {}", request.getEmail());
                sendConfirmationMail(waitingListNumber, refCode, request.getEmail());

            } catch (Exception e) {
                log.error("mail sending is failed for the user {}", e.getLocalizedMessage());
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        });

        log.info("Create waiting list number {}", response);
        return responseSuccess("waitingList number generated successfully!",
                ResponseCodeEnum.WAITINGLIST_NUMBER_GENERATED, response, USER_DETAILS);

    }

    @Override
    public ResponseObject verifyRefCode(String refCode) {
        try {
            WaitingListDetailsEntity waitingDetailsEntity = waitingListRepository.findByReferenceCode(refCode);
            if (waitingDetailsEntity != null) {
                return responseSuccess("Verified Successfully", ResponseCodeEnum.REFERENCE_CODE_VERIFIED_SUCCESSFULLY);
            }
            return responseError("Reference code Verification failed", ResponseCodeEnum.REFERENCE_CODE_VERIFIED_FAILED);
        } catch (Exception e) {
            log.error("Error occured in verifyRefCode {}", e.getMessage());
            throw new IecoRuntimeException(String.format(ERROR_WITH_CHECK_EMAIL_S, e.getMessage(), refCode),
                    e);
        }
    }

    @Override
    public ResponseObject updateDetails(WaitingListDetailsVO req) {

        try {
            WaitingListDetailsEntity customerDetailsEntity = waitingListRepository
                    .findByEmailIdIgnoreCase(req.getEmailId());
            if (customerDetailsEntity != null) {

                if (new NullCheck<>(req).withEmpty(WaitingListDetailsVO::getMobileNumber).isNotNullOrEmpty()) {
                    if (!isNumeric(req.getMobileNumber().trim()) || req.getMobileNumber().length() != 10) {

                        return AbstractResponse.responseError("Mobile Number is invalid",
                                ResponseCodeEnum.INVALID_MOBILE);
                    }
                    customerDetailsEntity.setFullName(req.getFullName());
                    customerDetailsEntity.setLinkedinURL(req.getLinkedinURL());
                    customerDetailsEntity.setMobileNumber(req.getMobileNumber());
                    customerDetailsEntity.setCheck1(req.getCheck1());
                    customerDetailsEntity.setCheck2(req.getCheck2());
                    customerDetailsEntity.setReferedByCode(req.getReferedByCode());
                    customerDetailsEntity.setReferedByEmail(req.getReferedByEmail());
                    customerDetailsEntity.setUpdatedTime(new Date());
                    waitingListRepository.save(customerDetailsEntity);

                    CompletableFuture.runAsync(() -> {
                        String url = appURL + REFCODE + customerDetailsEntity.getReferenceCode();
                        Integer currNum = customerDetailsEntity.getWaitingListNumber()
                                - customerDetailsEntity.getOffset();
                        String smsText = "Hey there, you have signed up for early access on Kotak Cherry and are currently on waitlist number "
                                + currNum
                                + ". Quick tip: To beat the queue – ask your fam to sign up using this link " + url;
                        SMSRequestVO smsreq = SMSRequestVO.builder().priority("1").srcAppCd(sourceAppId)
                                .text(smsText).to(req.getMobileNumber())
                                .msgReqID(String.valueOf(System.currentTimeMillis())).contentTemplateId("1107161236043209034").principalId(PRINCIPAL_ID).build();
                        sendSms(smsreq);

                    });

                } else {
                    updateDetailsSecondTime(req, customerDetailsEntity);
                    return responseSuccess(DETAILS_UPDATED_SUCCESSFULLY,
                            ResponseCodeEnum.DETAILS_UPADTED_SUCCESSFULLY);
                }

                if (new NullCheck<>(req).withEmpty(WaitingListDetailsVO::getReferedByCode).isNotNullOrEmpty()) {
                    log.info("Inside updating count of {}", req.getReferedByCode());
                    WaitingListDetailsEntity waitingDetailsEntity = waitingListRepository
                            .findByReferenceCode(req.getReferedByCode());
                    log.info("waitingDetailsEntity {}", waitingDetailsEntity);
                    if (waitingDetailsEntity != null) {
                        waitingDetailsEntity.setReferedCount(waitingDetailsEntity.getReferedCount() + 1);
                        waitingDetailsEntity.setOffset(waitingDetailsEntity.getOffset() + fetchDefaultSettings());
                        waitingDetailsEntity.setUpdatedTime(new Date());
                        waitingListRepository.save(waitingDetailsEntity);

                        CompletableFuture.runAsync(() -> {

                            Integer currNum = waitingDetailsEntity.getWaitingListNumber()
                                    - waitingDetailsEntity.getOffset();
                            triggerCrossedMilestoneSMS(waitingDetailsEntity, currNum);

                            if (waitingDetailsEntity.getWaitingListNumber() > 10
                                    && (currNum > 0 && currNum <= 10)) {
                                String url = appURL + "temp3?email=" + waitingDetailsEntity.getEmailId();
                                String smsText = "Take a bow! You are at no. " + currNum + " and are about to enter the world of Kotak Cherry any time now. Tap " + url + " to read more";
                                SMSRequestVO smsreq = SMSRequestVO.builder().priority("1").srcAppCd(sourceAppId)
                                        .text(smsText).to(waitingDetailsEntity.getMobileNumber())
                                        .msgReqID(String.valueOf(System.currentTimeMillis())).contentTemplateId("1107161236090614968").principalId(PRINCIPAL_ID).build();
                                sendSms(smsreq);

                            }
                            if (currNum > 1) {

                                String url = appURL + "temp4?email=" + waitingDetailsEntity.getEmailId();


                                String smsText = "You make quite an impression on your people. You just climbed up by " + waitingDetailsEntity.getOffset() + " steps and currently are at waitlist "
                                        + "no. " + currNum + ". Wanna go higher? Ask your buddies to sign up using this " + url + " - Kotak Cherry";
                                SMSRequestVO smsreq = SMSRequestVO.builder().priority("1").srcAppCd(sourceAppId)
                                        .text(smsText).to(waitingDetailsEntity.getMobileNumber())
                                        .msgReqID(String.valueOf(System.currentTimeMillis())).contentTemplateId("1107161235835634648").principalId(PRINCIPAL_ID).build();
                                sendSms(smsreq);
                            } else {
                                waitingDetailsEntity.setWaitingListNumber(1);
                                waitingListRepository.save(waitingDetailsEntity);
                            }

                        });
                    }
                }
                log.info("update Details success {}", req.getEmailId());

                return responseSuccess(DETAILS_UPDATED_SUCCESSFULLY, ResponseCodeEnum.DETAILS_UPADTED_SUCCESSFULLY);

            }
            log.info("update Details failed {}", req.getEmailId());
            return responseError("Deatils not found with provided email", ResponseCodeEnum.DETAILS_UPADTED_FAILED);

        } catch (Exception e) {
            throw new IecoRuntimeException(
                    String.format("%s [ERROR] with [updating details]: %s", e.getMessage(), req.getEmailId()), e);
        }

    }

    private void updateDetailsSecondTime(WaitingListDetailsVO request, WaitingListDetailsEntity customerDetailsEntity) {
        log.info("Üpdate details second time for the user {}", request.getEmailId());
        if (new NullCheck<>(request).withEmpty(WaitingListDetailsVO::getFullName).isNotNullOrEmpty()) {
            customerDetailsEntity.setFullName(request.getFullName());
        }
        if (new NullCheck<>(request).withEmpty(WaitingListDetailsVO::getLinkedinURL).isNotNullOrEmpty()) {
            customerDetailsEntity.setLinkedinURL(request.getLinkedinURL());
        }
        if (new NullCheck<>(request).withEmpty(WaitingListDetailsVO::getCheck1).isNotNullOrEmpty()) {
            customerDetailsEntity.setCheck1(request.getCheck1());
        }
        if (new NullCheck<>(request).withEmpty(WaitingListDetailsVO::getCheck2).isNotNullOrEmpty()) {
            customerDetailsEntity.setCheck2(request.getCheck2());
        }
        if (new NullCheck<>(request).withEmpty(WaitingListDetailsVO::getReferedByCode).isNotNullOrEmpty()) {
            customerDetailsEntity.setReferedByCode(request.getReferedByCode());
        }
        if (new NullCheck<>(request).withEmpty(WaitingListDetailsVO::getReferedByEmail).isNotNullOrEmpty()) {
            customerDetailsEntity.setReferedByEmail(request.getReferedByEmail());
        }
        if (new NullCheck<>(request).withEmpty(WaitingListDetailsVO::getCheck1).isNotNullOrEmpty()) {
            customerDetailsEntity.setReferedByEmail(request.getCheck1());
        }
        if (new NullCheck<>(request).withEmpty(WaitingListDetailsVO::getCheck2).isNotNullOrEmpty()) {
            customerDetailsEntity.setReferedByEmail(request.getCheck2());
        }
        waitingListRepository.save(customerDetailsEntity);

    }

    public ResponseObject saveInvitaionURL(IncomingEmail request) {
        if (new NullCheck<>(request).withEmpty(IncomingEmail::getInvitationURL).isNotNullOrEmpty()) {
            WaitingListDetailsEntity customerDetailsEntity = waitingListRepository
                    .findByEmailIdIgnoreCase(request.getEmail());

            customerDetailsEntity.setInvitationURL(request.getInvitationURL());
            waitingListRepository.save(customerDetailsEntity);
            return responseSuccess(DETAILS_UPDATED_SUCCESSFULLY, ResponseCodeEnum.DETAILS_UPADTED_SUCCESSFULLY);
        }
        return responseError("Invitaion URL should not empty", ResponseCodeEnum.DETAILS_UPADTED_FAILED);
    }

    @Override
    public ResponseObject checkAccess(String email) {
        List<String> mapKeyList = new ArrayList<String>();
        List<Object> attrList = new ArrayList<Object>();

        HttpHeaders headers = null;
        try {
            WaitingListDetailsEntity detailEntity = waitingListRepository.findByEmailIdIgnoreCase(email);
            if (detailEntity != null) {

                if (detailEntity.getIsAccessProvided().equalsIgnoreCase("y")) {
                    mapKeyList.addAll(Arrays.asList("isAccessProvided", "emailId"));
                    attrList = Arrays.asList(true, detailEntity.getEmailId());
                    return responseEntityMultipleDataCookieSuccess("", ResponseCodeEnum.ACCESS_PROVIDED, attrList,
                             mapKeyList);

                }
                mapKeyList.addAll(Arrays.asList("isAccessProvided", "emailId"));
                attrList = Arrays.asList(false, detailEntity.getEmailId());
                return responseEntityMultipleDataCookieFailure("", ResponseCodeEnum.ACCESS_NOT_PROVIDED, attrList,
                         mapKeyList);
            }
            return responseError("Email not exist!", ResponseCodeEnum.EMAIL_NOT_EXISTS);
        } catch (Exception e) {
            log.error("Error occured in checkAccess {}", email);
            throw new IecoRuntimeException(String.format(ERROR_WITH_CHECK_EMAIL_S, e.getMessage(), email), e);
        }
    }

    @Override
    public ResponseObject findPaginated(PaginationVO request, String token) {

        if (token != null && !token.isEmpty()) {
            List<String> mapKeyList = new ArrayList<String>();
            List<Object> attrList;
            HttpHeaders headers = null;
            if (validateToken(request.getEmailId(), token)) {

                Page<WaitingListDetailsEntity> pagedResult = null;

                Pageable paging = PageRequest.of(Integer.parseInt(request.getPageNum()) - 1,
                        Integer.parseInt(request.getLimit()), Sort.by("waitingListNumber").ascending());
                if (new NullCheck<>(request).withEmpty(PaginationVO::getSearchTerm).isNotNullOrEmpty()) {
                    pagedResult = waitingListRepository.findByIsAccessProvided(request.getSearchTerm(), paging);
                } else {
                    pagedResult = waitingListRepository.findAll(paging);
                }
                mapKeyList.addAll(Arrays.asList("records", "totalRecords"));
                attrList = Arrays.asList(pagedResult.toList(), pagedResult.getTotalElements());
                return responseEntityMultipleDataCookieSuccess("Records fetched successfully!",
                        ResponseCodeEnum.ACCESS_PROVIDED, attrList, mapKeyList);
            }
            return responseError(PROVIDE_VALID_TOKEN, ResponseCodeEnum.EMAIL_NOT_EXISTS);
        }

        return responseError(AUTHENTICATION_IS_REQUIRED_TO_ACCESS_THIS_RESOURCE, ResponseCodeEnum.EMAIL_NOT_EXISTS);
    }

    @Override
    public ResponseObject findByEmailIdContaing(IncomingEmail request, String chars, String token) {
        List<String> mapKeyList = new ArrayList<String>();
        List<Object> attrList = new ArrayList<Object>();

        HttpHeaders headers = null;
        try {
            if (token != null && !token.isEmpty()) {

                if (validateToken(request.getEmail(), token)) {
                    List<WaitingListDetailsEntity> detailEntity = waitingListRepository
                            .findByEmailIdContainingIgnoreCase(chars);
                    if (!CollectionUtils.isEmpty(detailEntity)) {
                        mapKeyList.addAll(Arrays.asList("searchRecords"));
                        attrList = Arrays.asList(detailEntity);
                        return responseEntityMultipleDataCookieSuccess("", ResponseCodeEnum.ACCESS_PROVIDED, attrList,
                                mapKeyList);

                    }
                    return responseError("List not found!", ResponseCodeEnum.EMAIL_NOT_EXISTS);
                }
                return responseError(PROVIDE_VALID_TOKEN, ResponseCodeEnum.EMAIL_NOT_EXISTS);
            }
            return responseError(AUTHENTICATION_IS_REQUIRED_TO_ACCESS_THIS_RESOURCE,
                    ResponseCodeEnum.EMAIL_NOT_EXISTS);
        } catch (Exception e) {
            throw new IecoRuntimeException(String.format(ERROR_WITH_CHECK_EMAIL_S, e.getMessage(), chars), e);
        }

    }

    @Override
    public ResponseObject login(IncomingEmail request) {
        List<String> mapKeyList = new ArrayList<String>();
        List<Object> attrList = new ArrayList<Object>();

        HttpHeaders headers = null;
        Optional<AdminDetails> adminDetails = adminDetailsRepository.findByEmailId(request.getEmail());
        if (adminDetails.isPresent()) {
            if (request.getPassword().equals(adminDetails.get().getPassword())) {
                String token = UUID.randomUUID().toString();
                AdminDetails userDetails = adminDetails.get();
                userDetails.setToken(token);
                adminDetailsRepository.save(userDetails);
                mapKeyList.add("token");
                attrList.add(token);
                return responseEntityMultipleDataCookieSuccess("login success!", ResponseCodeEnum.LOGIN_SUCCESS,
                        attrList, mapKeyList);

            } else {
                return responseError("Username and password doesn't matched!",
                        ResponseCodeEnum.LOGIN_USERNAME_PWD_INCORRECT);
            }
        }
        return responseError("User not found!", ResponseCodeEnum.LOGIN_USERNAME_NOT_FOUND);

    }

    @Transactional
    public ResponseObject provideAccess(ProvideAccessVO request, String token) {

        try {
            if (token != null && !token.isEmpty()) {

                if (validateToken(request.getUserId(), token)) {

                    waitingListRepository.updateAccessFields(request.getEmailList(), 0, "Y", new Date(),
                            request.getUserId());

                    CompletableFuture.runAsync(() -> {


                        String smsText = "Yipeee! You are the newest member of the Kotak Cherry gang! Let us make your dreams come true. Tap " + cherryURL + " to start exploring Cherry.";
                        if (request.getEmailList().size() > 1) {
                            List<WaitingListDetailsEntity> listEntity = waitingListRepository
                                    .findAllByEmailIdIn(request.getEmailList());
                            for (WaitingListDetailsEntity waitingDetailsEntity : listEntity) {
                                SMSRequestVO smsreq = SMSRequestVO.builder().priority("1").srcAppCd(sourceAppId)
                                        .text(smsText).to(waitingDetailsEntity.getMobileNumber())
                                        .msgReqID(String.valueOf(System.currentTimeMillis())).contentTemplateId("1107161235764020824").principalId(PRINCIPAL_ID).build();
                                sendSms(smsreq);
                            }

                        } else if (request.getEmailList().size() == 1) {
                            WaitingListDetailsEntity waitingDetailsEntity = waitingListRepository
                                    .findByEmailIdIgnoreCase(request.getEmailList().get(0));
                            SMSRequestVO smsreq = SMSRequestVO.builder().priority("1").srcAppCd(sourceAppId)
                                    .text(smsText).to(waitingDetailsEntity.getMobileNumber())
                                    .msgReqID(String.valueOf(System.currentTimeMillis())).contentTemplateId("1107161235764020824").principalId(PRINCIPAL_ID).build();
                            sendSms(smsreq);

                        }
                    });

                    return responseSuccess(DETAILS_UPDATED_SUCCESSFULLY,
                            ResponseCodeEnum.DETAILS_UPADTED_SUCCESSFULLY);
                }
                return responseError(PROVIDE_VALID_TOKEN, ResponseCodeEnum.EMAIL_NOT_EXISTS);
            }

            return responseError(AUTHENTICATION_IS_REQUIRED_TO_ACCESS_THIS_RESOURCE,
                    ResponseCodeEnum.EMAIL_NOT_EXISTS);
        } catch (Exception e) {
            log.error("error while providing access {}",e.getLocalizedMessage());
            throw new IecoRuntimeException(
                    String.format(ERROR_WITH_CHECK_EMAIL_S, e.getMessage(), "provide access"), e);
        }
    }

    @Transactional
    public ResponseObject deleteUser(ProvideAccessVO request, String token) {
        try {
            if (token != null && !token.isEmpty()) {

                if (validateToken(request.getUserId(), token)) {
                    waitingListRepository.deleteUser(request.getEmailList());
                    return responseSuccess("Users deleted successfully", ResponseCodeEnum.DETAILS_UPADTED_SUCCESSFULLY);
                }
                return responseError(PROVIDE_VALID_TOKEN, ResponseCodeEnum.TOKEN_VALIDATION_FAILED);
            }
            return responseError(AUTHENTICATION_IS_REQUIRED_TO_ACCESS_THIS_RESOURCE,
                    ResponseCodeEnum.EMAIL_NOT_EXISTS);
        } catch (Exception e) {
            log.error("error while deleting user {}", e.getLocalizedMessage());
            throw new IecoRuntimeException(
                    String.format(ERROR_WITH_CHECK_EMAIL_S, e.getMessage(), "deleting user"), e);
        }
    }

    @Override
    public ResponseObject logout(IncomingEmail request, String token) {
        try {
            if (token != null && !token.isEmpty()) {

                if (validateToken(request.getEmail(), token)) {
                    AdminDetails adminDetails = adminDetailsRepository.findByEmailId(request.getEmail()).get();
                    adminDetails.setToken("");
                    adminDetailsRepository.save(adminDetails);
                    return responseSuccess("User logout successfully", ResponseCodeEnum.DETAILS_UPADTED_SUCCESSFULLY);
                }
                return responseError(PROVIDE_VALID_TOKEN, ResponseCodeEnum.TOKEN_VALIDATION_FAILED);
            }
            return responseError(AUTHENTICATION_IS_REQUIRED_TO_ACCESS_THIS_RESOURCE,
                    ResponseCodeEnum.EMAIL_NOT_EXISTS);
        } catch (Exception e) {
            log.error("error while deleting user {}", e.getLocalizedMessage());
            throw new IecoRuntimeException(
                    String.format(ERROR_WITH_CHECK_EMAIL_S, e.getMessage(), "deleting user"), e);
        }
    }

    @Override
    public void generateCsvResponse(HttpServletResponse response, String token) {

        CSVPrinter csvPrinter = null;

        try {

            if (token != null && !token.isEmpty()) {

                Optional<AdminDetails> adminDetails = adminDetailsRepository.findByToken(token);
                if (adminDetails.isPresent() && adminDetails.get().getToken().equals(token)) {
                        log.info(token);
                        Date date = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm");
                        String dateString = sdf.format(date);
                        String filename = "WaitingListDetails_" + dateString + ".csv";
                        List<WaitingListDetailsEntity> listenity = waitingListRepository.findAll();
                        response.setContentType("text/csv");
                        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=\"" + filename + "\"");
                        csvPrinter = new CSVPrinter(response.getWriter(),
                                CSVFormat.DEFAULT.withHeader("Email", "WaitiListNumber", "Mobile Number", "Full Name",
                                        "Referal Code", "Linkedin URL", "OffSet", "Refered Count", "Refered By Code",
                                        "Refered By Email", "Source", "Medium", "Extras", "Created On",
                                        "Access Allowed", "Access Allowed On", "Access Allowed By", "Check1", "Check2",
                                        "Invitation URL"));

                        for (WaitingListDetailsEntity user : listenity) {
                            String allowedOn = "";
                            if (new NullCheck<>(user).withEmpty(WaitingListDetailsEntity::getAccessAllowedOn)
                                    .isNotNullOrEmpty()) {
                                allowedOn = new SimpleDateFormat("dd/MM/yyyy").format(user.getAccessAllowedOn());
                            }
                            csvPrinter.printRecord(Arrays.asList(user.getEmailId(), user.getWaitingListNumber(),
                                    user.getMobileNumber(), user.getFullName(), user.getReferenceCode(),
                                    user.getLinkedinURL(), user.getOffset(), user.getReferedCount(),
                                    user.getReferedByCode(), user.getReferedByEmail(), user.getSource(),
                                    user.getMedium(), user.getExtras(),
                                    new SimpleDateFormat("dd/MM/yyyy").format(user.getCreatedTime()),
                                    user.getIsAccessProvided(), allowedOn, user.getAccessAllowedBy(), user.getCheck1(),
                                    user.getCheck2(), user.getInvitationURL()));
                        }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (csvPrinter != null)
                try {
                    csvPrinter.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
    }

    private long generateRandomNumber(int n) throws NoSuchAlgorithmException {
        long min = (long) Math.pow(10, n - 1);
        /**
         *  Fortify issue started, commented line 763 and added 764
         */
        return SecureRandom.getInstanceStrong().nextInt((int) (min * 10));
        /**
         *  Fortify issue ended
         */

    }

    boolean validateToken(String email, String token) {
        Optional<AdminDetails> adminDetails = adminDetailsRepository.findByEmailId(email);
        if (adminDetails.isPresent() && adminDetails.get().getToken().equals(token)) {
                return true;
        }
        return false;
    }

    Integer fetchDefaultSettings() {

        List<WaitingListSettingsEntity> settings = waitingListSettingsRepository.findAll();
        if (settings.size() > 0) {
            log.info("settings {}", settings);
            log.info("Offset ",settings.get(0).getOffset());
            return settings.get(0).getOffset();
        }

        return 0;
    }


    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return patternToCheckInteger.matcher(strNum).matches();
    }

    public void sendConfirmationMail(Integer waitingListumber, String refCode, String toEmail) {
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();

        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("waitListNumber", waitingListumber);
        velocityContext.put("referenceCode", refCode);
        velocityContext.put("uniqueLink", appURL + REFCODE + refCode);
        velocityContext.put("domain", appURL);

        Resource resourceGif = new ClassPathResource("images/Cherry_Beta-mailer1_GIF.gif");
        Resource resourceSmile = new ClassPathResource("images/smile.png");
        Resource resourceThumb = new ClassPathResource("images/thumb.png");
        Resource resourceFb = new ClassPathResource("images/fb.png");
        Resource resourceInsta = new ClassPathResource("images/insta.png");
        Resource resourceCherryImage = new ClassPathResource("images/Cherry-Img.png");

        StringWriter stringWriter = new StringWriter();
        velocityEngine.mergeTemplate("templates/WaitListConfirmationMail.vm", "UTF-8", velocityContext, stringWriter);

        // this is assuming you're sending HTML email using MimeMessageHelper

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom(emailFrom);
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setSubject(emailSubject);
        mimeMessageHelper.setSentDate(new Date());
        mimeMessageHelper.setText(stringWriter.toString(), true);
        mimeMessageHelper.addInline("Cherry_Beta-mailer1_GIF", resourceGif);
        mimeMessageHelper.addInline("smile", resourceSmile);
        mimeMessageHelper.addInline("thumb", resourceThumb);
        mimeMessageHelper.addInline("fb", resourceFb);
        mimeMessageHelper.addInline("insta", resourceInsta);
        mimeMessageHelper.addInline("cherryImage", resourceCherryImage);
        mailSender.send(mimeMessage);
        log.info("mail sent successfully for the user {}", toEmail);

    }

    public ResponseObject debounceCheck(String email) {
        DebounceResponse debounceResponse = null;
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.debounce.io/v1")
                    .queryParam("email", email).queryParam("api", "5fcb5938ca76a");

            ResponseEntity<String> responseEntity = restUtility.get(builder.toUriString(), new HashMap<>(), String.class);
            log.info("debounce response {}", responseEntity.getBody());
            debounceResponse = mapper.readValue(responseEntity.getBody(), DebounceResponse.class);
            if (restUtility.checkStatus(responseEntity)) {

                if (debounceResponse.getSuccess().equalsIgnoreCase("1")
                        && debounceResponse.getDebounce().getCode().equalsIgnoreCase("5")) {
                    return responseSuccess(debounceResponse.getDebounce().getReason(),
                            ResponseCodeEnum.DEBOUNCE_VERIFICATION_SUCCESS);
                }
                return responseError(debounceResponse.getDebounce().getReason(),
                        ResponseCodeEnum.DEBOUNCE_VERIFICATION_FAILED);

            }
            return responseError(debounceResponse.getDebounce().getError(),
                    ResponseCodeEnum.DEBOUNCE_VERIFICATION_SUCCESS);
        } catch (Exception e) {
            log.error("Exception while checking the debounce {}", e.getLocalizedMessage());
            throw new IecoRuntimeException(e.getMessage(), e.getCause());
        }
    }

    public String sendSms(SMSRequestVO smsreq) {

        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
        SMSAPIReq sMSAPIReq = new SMSAPIReq();
        Header value = new Header();
        value.setRequestID(smsreq.getMsgReqID());
        value.setSrcAppCd(smsreq.getSrcAppCd());

        sMSAPIReq.setHeader(value);
        SMSAPIReq.Req smsRequestBody = new SMSAPIReq.Req(); // Request Body
        smsRequestBody.setMsgReqID(smsreq.getMsgReqID());
        smsRequestBody.setPriority(smsreq.getPriority());
        smsRequestBody.setSubAppID("");
        smsRequestBody.setTimestamp(sdf.format(new Date()));
        smsRequestBody.setType("TXN");

        SMSAPIReq.Req.Messages.Message messageBody = new SMSAPIReq.Req.Messages.Message(); // Message
        // Body
        messageBody.setFrom(from);
        messageBody.setText(smsreq.getText());

        SMSAPIReq.Req.Messages.Message.Destinations.Destination destinationBody = new SMSAPIReq.Req.Messages.Message.Destinations.Destination();// destination
        // body
        destinationBody.setTo(smsreq.getTo());
        SMSAPIReq.Req.Messages.Message.Destinations destination = new SMSAPIReq.Req.Messages.Message.Destinations();
        destination.getDestination().add(destinationBody);

        messageBody.setDestinations(destination);

        SMSAPIReq.Req.Messages.Message.IndiaDlt indiaDlt = new SMSAPIReq.Req.Messages.Message.IndiaDlt();
        indiaDlt.setPrincipalEntityId(smsreq.getPrincipalId());
        indiaDlt.setContentTemplateId(smsreq.getContentTemplateId());
        messageBody.setIndiaDlt(indiaDlt);

        SMSAPIReq.Req.Messages message = new SMSAPIReq.Req.Messages();
        message.getMessage().add(messageBody);
        smsRequestBody.setMessages(message);

        smsRequestBody.setTrack("URL");
        smsRequestBody.setURLShortening(true);

        sMSAPIReq.setReq(smsRequestBody);

        try {
            String s = transformer.smstransformJSONToXML(sMSAPIReq);
            log.info("Payload {}", s);
            String res = gatewayClient.sendSMS(s, "Bearer " + getFinacleAccessToken());
            SMSResponseVO vo = transformer.smsftransformXMLToJSON(res);
            log.info("sms response {}", vo);
        } catch (JAXBException e) {
            log.error("error occured while transforming xml to pojo {}",e.getLocalizedMessage());

        }
        return "SUCCESS";
    }

    public String getFinacleAccessToken() {
        GatewayTokenResponse accessToken = null;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, Object> requestMap = new LinkedMultiValueMap<String, Object>();

        requestMap.add("grant_type", grantType);
        requestMap.add("client_id", clientId);
        requestMap.add("client_secret", clientSecret);
        accessToken = tokenClient.getToken(requestMap);
        log.info("Access token {}", accessToken.getAccess_token());
        return accessToken.getAccess_token();

    }

    private void triggerCrossedMilestoneSMS(WaitingListDetailsEntity waitingDetailsEntity,
                                            Integer currentWaitingListNumber) {
        Integer firstMileStone = 5000;
        Integer secondMileStone = 1000;
        Integer thirdMileStone = 500;
        Integer fourthMileStone = 100;
        String url = appURL + "temp2?email=" + waitingDetailsEntity.getEmailId();
        String smsText = null;
        Integer offsetsetting = fetchDefaultSettings();
        if (waitingDetailsEntity.getWaitingListNumber() >= 100 && currentWaitingListNumber < fourthMileStone && fourthMileStone - offsetsetting < currentWaitingListNumber) {

            smsText = YOU_ARE_A_STAR_YOU_HAVE_CROSSED_A_MILESTONE_OF + fourthMileStone
                    + WANNA_MAKE_YOUR_WAY_UP_HERE_IS_HOW + url + KOTAK_CHERRY;

        } else if (waitingDetailsEntity.getWaitingListNumber() >= 500 && currentWaitingListNumber < thirdMileStone && thirdMileStone - offsetsetting < currentWaitingListNumber) {

            smsText = YOU_ARE_A_STAR_YOU_HAVE_CROSSED_A_MILESTONE_OF + thirdMileStone
                    + WANNA_MAKE_YOUR_WAY_UP_HERE_IS_HOW + url + KOTAK_CHERRY;

        } else if (waitingDetailsEntity.getWaitingListNumber() >= 1000 && currentWaitingListNumber < secondMileStone && secondMileStone - offsetsetting < currentWaitingListNumber) {

            smsText = YOU_ARE_A_STAR_YOU_HAVE_CROSSED_A_MILESTONE_OF + secondMileStone
                    + WANNA_MAKE_YOUR_WAY_UP_HERE_IS_HOW + url + KOTAK_CHERRY;

        } else if (waitingDetailsEntity.getWaitingListNumber() >= 1000 && currentWaitingListNumber < firstMileStone && firstMileStone - offsetsetting < currentWaitingListNumber) {

            smsText = YOU_ARE_A_STAR_YOU_HAVE_CROSSED_A_MILESTONE_OF + firstMileStone
                    + WANNA_MAKE_YOUR_WAY_UP_HERE_IS_HOW + url + KOTAK_CHERRY;
        }
        if (smsText != null) {
            log.info("triggering crossed milestone sms");
            SMSRequestVO smsreq = SMSRequestVO.builder().priority("1").srcAppCd(sourceAppId).text(smsText)
                    .to(waitingDetailsEntity.getMobileNumber()).msgReqID(String.valueOf(System.currentTimeMillis()))
                    .contentTemplateId("1107161235870536587").principalId(PRINCIPAL_ID).build();
            sendSms(smsreq);
        }
    }

    @Override
    public ResponseObject reduceWaitingListNumber(String minValue, String token) {

        try {

            if (token != null && !token.isEmpty()) {

                Optional<AdminDetails> adminDetails = adminDetailsRepository.findByToken(token);
                if (adminDetails.isPresent()) {
                    if (adminDetails.get().getToken().equals(token)) {
                        Integer minimizeValue = Integer.parseInt(minValue);
                        List<WaitingListDetailsEntity> waitingList = waitingListRepository.findByWaitingListNumberGreaterThanEqual(minimizeValue);
                        log.info("number of records fetched from db {}", waitingList.size());
                        List<WaitingListDetailsEntity> updatedList = new ArrayList<>();
                        for (WaitingListDetailsEntity userDetails : waitingList) {
                            userDetails.setWaitingListNumber(userDetails.getWaitingListNumber() - minimizeValue);
                            updatedList.add(userDetails);

                        }
                        waitingListRepository.saveAll(updatedList);

                        return responseSuccess("Updated successfully", ResponseCodeEnum.DETAILS_UPADTED_SUCCESSFULLY);
                    }

                    return responseError(PROVIDE_VALID_TOKEN, ResponseCodeEnum.TOKEN_VALIDATION_FAILED);
                }
            }
            return responseError("Authentication is required to perform this action!",
                    ResponseCodeEnum.EMAIL_NOT_EXISTS);
        } catch (Exception e) {
            log.error("Exception in reduceWaitingListNumber {}",e.getLocalizedMessage());
            throw new IecoRuntimeException(e.getMessage(), e.getCause());
        }
    }
}