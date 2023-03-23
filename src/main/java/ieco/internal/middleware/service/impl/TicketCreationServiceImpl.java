package ieco.internal.middleware.service.impl;

import ieco.internal.middleware.domain.request.*;
import ieco.internal.middleware.domain.response.*;
import ieco.internal.middleware.feignclient.CleverTapClient;
import ieco.internal.middleware.feignclient.ZohoClient;
import ieco.internal.middleware.model.ZohoTicketsFromWhatsApp;
import ieco.internal.middleware.repository.WhatsAppTicketRepository;
import ieco.internal.middleware.service.TicketCreationService;
import ieco.internal.middleware.service.ZohoService;
import ieco.internal.middleware.util.CustomerUtil;
import ieco.internal.middleware.util.ZohoUtility;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class TicketCreationServiceImpl implements TicketCreationService {

    public static final String TICKET_CREATE_CONTACT_SEARCHING_BY_CUSTOMER_ID_RESPONSE = "ticketCreate--Contact searching  by customer ID Response {}";
    private static final String DEFAULT_DEPARTMENT = "KIAL";

    private static final String KIAL_DEPARTMENT_ID = "17634000000072420";
    public static final String SUCCESS = "Success";
    public static final String OK = "200 OK";
    public static final String CF_KOTAK_360 = "cf_kotak_360";
    public static final String TICKET_CREATE_SEARCHING_CONTACT_IN_ZOHO_QUERY_PARAM = "ticketCreate--Searching contact in zoho queryParam :{}";
    public static final String CONTACT_FOUND_IN_ZOHO_TRYING_TO_CREATE_TICKET = "Contact Found in Zoho, trying to create Ticket {}";
    public static final String AUTOMATIC = "Automatic";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String CONTACT_NOT_FOUND_IN_ZOHO = "404 Contact not found in Zoho";

    @Value("${ticketCreation}")
    private String ticketCreationURL;

    @Value("${searchContact}")
    private String searchContactURL;

    @Value("${orgId}")
    private String orgId;

    @Autowired
    private ZohoClient zohoClient;

    @Value("${zohoenv}")
    private String environment;

    @Value("${isZohoEnabled}")
    private boolean isZohoEnable;

    @Value("${cleverTapAccountId}")
    private String cleverTapAccountId;

    @Value("${cleverTapPasscode}")
    private String cleverTapPasscode;

    @Autowired
    private CleverTapClient cleverTapClient;

    @Autowired
    private ZohoUtility zohoUtility;

    @Autowired
    private ZohoService zohoService;

    @Autowired
    private CustomerUtil customerUtil;

    @Autowired
    private WhatsAppTicketRepository whatsAppTicketRepository;

    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public ResponseObject ticketCreate(MiddlewareRequest middlewareRequest) {
        ResponseObject res = null;
        try {

            if (isZohoEnable) {
                ZohoTicketCache ticketCache =
                        zohoUtility.checkForZohoTicketCache(middlewareRequest.getCustomerId());
                log.info("ticket cache {}", ticketCache);
                if (ticketCache != null) {
                    log.info("ticket presented in cache {}", ticketCache.getTicketId(),
                            ticketCache.getCustomerId());
                    return updateTicket(middlewareRequest, ticketCache.getTicketId());
                } else {
                    return createTicket(middlewareRequest);
                }

            } // is zoho enabled check
            else {
                log.info("zoho disabled.... giving dummy response or customerId is null from clevertap");
                ResponseObject resObject = new ResponseObject();
                resObject.setMessage(SUCCESS);
                resObject.setTimeStamp(System.currentTimeMillis());
                resObject.setTicketDetails("123");
                resObject.setTicketId("9876543");
                resObject.setStatus(OK);
                return resObject;
            }

        } catch (Exception e) {
            e.printStackTrace();
            res = new ResponseObject();
            res.setMessage("Error Occurred while creating Ticket-" + e.getCause());
            res.setStatus("500 Internal Server Error");
            log.info("Ticket creation Failed for request {}", middlewareRequest);
            log.error("Exception in ticketCreate {}",e.getLocalizedMessage());
            return res;
        }
    }

    private ResponseObject updateTicket(MiddlewareRequest middlewareRequest, String ticketId) {
        log.info("updating the ticket....");
        ZohoTicketUpdateRequest zohoTicketUpdateRequest =
                ZohoTicketUpdateRequest.builder().subject(middlewareRequest.getIssueDescription()).build();

        // statusType
        TicketUpdateResponse getTicketcResponse = zohoClient.getTicket(ticketId);
        if (getTicketcResponse.getStatus().equalsIgnoreCase("Closed")) {
            zohoTicketUpdateRequest.setStatus("Open");
        }


        TicketUpdateResponse tcResponse = zohoClient.ticketUpdate(zohoTicketUpdateRequest, ticketId);
        ResponseObject resObject = new ResponseObject();
        resObject.setMessage(SUCCESS);
        resObject.setTimeStamp(System.currentTimeMillis());
        resObject.setTicketDetails(tcResponse.getTicketNumber());
        resObject.setTicketId(tcResponse.getId());
        resObject.setStatus(OK);
        return resObject;
    }

    private ResponseObject createTicket(MiddlewareRequest middlewareRequest) {

        ResponseEntity<ContactDetailsResponse> searchContactRes = null;
        log.info("request for ticket creation in post login {}", middlewareRequest);
        if (middlewareRequest.getCustomerId() != null && middlewareRequest.getCustomerId() != "") {
            String queryParam = CF_KOTAK_360 + ":" + middlewareRequest.getCustomerId();
            log.info(TICKET_CREATE_SEARCHING_CONTACT_IN_ZOHO_QUERY_PARAM, queryParam);
            searchContactRes =
                    zohoService.searchContact(CF_KOTAK_360, middlewareRequest.getCustomerId());
            log.info("Post login Contact searching Response by customer ID status and Id {} {}",
                    searchContactRes.getStatusCodeValue(), middlewareRequest.getCustomerId());
            log.info(TICKET_CREATE_CONTACT_SEARCHING_BY_CUSTOMER_ID_RESPONSE, searchContactRes);
            if (searchContactRes.getStatusCodeValue() == 204) {
                log.info("Contact ID not found in Zoho" + middlewareRequest.getCustomerId());
                if (middlewareRequest.getEmail() != null) {
                    log.info("Contact ID not found in Zoho, searching customer by email ID"
                            + middlewareRequest.getEmail());
                    searchContactRes = zohoClient.searchContact("email" + ":" + middlewareRequest.getEmail());
                    log.info(
                            "ticketCreate--Post login Contact searching Response by Email status and EMail {} {}",
                            searchContactRes.getStatusCodeValue(), middlewareRequest.getEmail());
                    log.info("ticketCreate--Contact searching Response by Email {}", searchContactRes);
                    middlewareRequest
                            .setCustomerId(customerUtil.getCustomerId(middlewareRequest.getEmail()).toString());
                    if (searchContactRes.getStatusCodeValue() == 204) {
                        log.info("Email ID not found in Zoho--" + middlewareRequest.getEmail());
                    }
                } else {
                    log.info("Email ID is null for contact search in zoho");
                    ResponseObject resObject = new ResponseObject();
                    resObject.setMessage("EmailID cannot be null " + middlewareRequest.getCustomerId());
                    resObject.setTimeStamp(System.currentTimeMillis());
                    resObject.setStatus("Failure");
                    return resObject;
                }
            }
        } else {
            if (middlewareRequest.getEmail() != null) {
                log.info("Contact ID not found in Zoho, searching customer by email ID"
                        + middlewareRequest.getEmail());
                searchContactRes = zohoClient.searchContact("email" + ":" + middlewareRequest.getEmail());
                log.info(
                        "ticketCreate--Post login Contact searching Response by Email status and EMail {} {}",
                        searchContactRes.getStatusCodeValue(), middlewareRequest.getEmail());
                log.info("ticketCreate--Contact searching Response by Email {}", searchContactRes);
                // setting customerId for ticket creation
                middlewareRequest
                        .setCustomerId(customerUtil.getCustomerId(middlewareRequest.getEmail()).toString());
                if (searchContactRes.getStatusCodeValue() == 204) {
                    log.info("Email ID not found in Zoho--" + middlewareRequest.getEmail());
                }
            } else {
                log.info("Customer ID and Email ID is null for ticket Creation {}", middlewareRequest);

                return ResponseObject.builder()
                        .message("Contact not found in zoho with the customer ID and Email ID "
                                + middlewareRequest.getCustomerId() + "-" + middlewareRequest.getEmail())
                        .timeStamp(System.currentTimeMillis()).status("Failure").build();

            }

        }


        if (searchContactRes.getStatusCodeValue() == 200) {
            log.info(CONTACT_FOUND_IN_ZOHO_TRYING_TO_CREATE_TICKET, middlewareRequest);
            String contactId = searchContactRes.getBody().getContactResponse().get(0).getId();


            TicketCreationRequest tcRequest = new TicketCreationRequest();
            DeskContactCreationCustomFields cf = new DeskContactCreationCustomFields();
            cf.setCf_environment(environment);

            cf.setCf_sr_nature("FTR");
            cf.setCf_crn_type("Individual");
            cf.setCf_sr_group("");
            cf.setCf_ieco_id(middlewareRequest.getCustomerId());
            cf.setCf_kotak_360_id(middlewareRequest.getCustomerId());
            cf.setCf_product_type(middlewareRequest.getProductCategory());

            cf.setCf_sr_classification("Service Request");
            cf.setCf_ticket_department(DEFAULT_DEPARTMENT);
            cf.setCf_creation_mode(AUTOMATIC);
            cf.setCf_created_date(new SimpleDateFormat(YYYY_MM_DD).format(new Date()));

            tcRequest.setEmail(middlewareRequest.getEmail());
            tcRequest.setSubject("Ticket has been created");
            // DepartmentUtil
            // .getDeptID(DepartmentUtil.getdepartment(middlewareRequest.getProductCategory()))
            tcRequest.setDepartmentId(KIAL_DEPARTMENT_ID);
            tcRequest.setContactId(contactId);
            tcRequest.setDescription(middlewareRequest.getIssueDescription());
            tcRequest.setChannel(
                    middlewareRequest.getChannel() != null && !middlewareRequest.getChannel().isEmpty()
                            ? middlewareRequest.getChannel()
                            : "Chatbot");
            if (middlewareRequest.isFromClevertap()) {
                tcRequest.setSubject(middlewareRequest.getIssueDescription());
                tcRequest.setPriority("High");
                tcRequest.setChannel("Clevertap");
                cf.setCf_customer_journey("On-boarding");
                if (middlewareRequest.getIssueDescription().equalsIgnoreCase("MF Cart Real Time Calling"))
                    cf.setCf_customer_journey("Transaction");
            }
            tcRequest.setCf(cf);
            log.info("ticket creation request {}", tcRequest);

            TicketCreationResponse tcResponse = zohoClient.ticketCreate(tcRequest);
            log.info("Ticket ID is {}", tcResponse.getId());


            storeInCache(tcResponse.getId(), middlewareRequest.getCustomerId()
            );
            return ResponseObject.builder().message(SUCCESS).timeStamp(System.currentTimeMillis())
                    .ticketDetails(tcResponse.getTicketNumber()).ticketId(tcResponse.getId()).status(OK)
                    .build();

        } else {
            log.info("Contact ID not found in Zoho with the customer ID and Email ID "
                    + middlewareRequest.getCustomerId() + "-" + middlewareRequest.getEmail());

            return ResponseObject.builder()
                    .message("Contact not found in zoho with the customer ID and Email ID "
                            + middlewareRequest.getCustomerId() + "-" + middlewareRequest.getEmail())
                    .timeStamp(System.currentTimeMillis()).status(CONTACT_NOT_FOUND_IN_ZOHO).build();
        }
    }

    private void storeInCache(String ticketId, String customerId) {
        log.info("ticket id storing in cache......");
        ZohoTicketCache ticketCache =
                ZohoTicketCache.builder().customerId(customerId).ticketId(ticketId).build();
        zohoUtility.storeTicketDetailsInCache(ticketCache);
    }

    public ResponseObject createTicketForWahtsAppChat(WhatsAppChatRequest req) {

        log.info("request for ticket creation from whatsapp {}", req);

        String queryParam = CF_KOTAK_360 + ":" + req.getCustomerId();
        log.info(TICKET_CREATE_SEARCHING_CONTACT_IN_ZOHO_QUERY_PARAM, queryParam);
        ResponseEntity<ContactDetailsResponse> searchContactRes =
                zohoService.searchContact(CF_KOTAK_360, req.getCustomerId());
        log.info("Contact searching Response by customer ID status and Id {} {}",
                searchContactRes.getStatusCodeValue(), req.getCustomerId());
        log.info(TICKET_CREATE_CONTACT_SEARCHING_BY_CUSTOMER_ID_RESPONSE, searchContactRes);

        if (searchContactRes.getStatusCodeValue() == 200) {
            log.info(CONTACT_FOUND_IN_ZOHO_TRYING_TO_CREATE_TICKET, req);

            TicketCreationRequest tcRequest = TicketCreationRequest.builder().subject("WhatsAppChat")
                    .departmentId(KIAL_DEPARTMENT_ID)
                    .contactId(searchContactRes.getBody().getContactResponse().get(0).getId())
                    // .description(req.getIssueDescription()).channel(req.getChannel())
                    .status(req.getZohoStatus())
                    .channel(req.getChannel())
                    .cf(DeskContactCreationCustomFields.builder()
                            .cf_ieco_id(String.valueOf(req.getCustomerId()))
                            .cf_kotak_360_id(String.valueOf(req.getCustomerId())).cf_creation_mode(AUTOMATIC)
                            .cf_created_date(new SimpleDateFormat(YYYY_MM_DD).format(new Date()))
                            .cf_product_type(req.getProductCategory()).cf_disposition(req.getDisposition())
                            .cf_notes(req.getNotes() != null && !req.getNotes().isEmpty() ? req.getNotes() : "")
                            .build())
                    .build();

            log.info("ticket creation request {}", tcRequest);
            TicketCreationResponse tcResponse = zohoClient.ticketCreate(tcRequest);

            log.info("Ticket ID is {}", tcResponse.getId());

            return ResponseObject.builder().message(SUCCESS).timeStamp(System.currentTimeMillis())
                    .ticketDetails(tcResponse.getTicketNumber()).ticketId(tcResponse.getId()).status(OK)
                    .build();

        } else {
            log.info("Contact ID not found in Zoho with the customer ID " + req.getCustomerId());

            return ResponseObject.builder()
                    .message("Contact not found in zoho with the customer ID " + req.getCustomerId())
                    .timeStamp(System.currentTimeMillis()).status(CONTACT_NOT_FOUND_IN_ZOHO).build();

        }

    }

    public ResponseObject getZohoTicketDetails(WhatsAppChatRequest req) {
        log.info("WhatsAppChatRequest req {}", req);
        ResponseObject createWhatsAppChatResponseObject = createTicketForWahtsAppChat(req);
        log.info("createWhatsAppChatResponseObject == {}", createWhatsAppChatResponseObject);
        return createWhatsAppChatResponseObject;
    }

    public ResponseObject getZohoTicketDetails_bkp(WhatsAppChatRequest req) {
        log.info("WhatsAppChatRequest req {}", req);

        Optional<ZohoTicketsFromWhatsApp> zohoTicketFromWhatsApp =
                whatsAppTicketRepository.findByCustomerId(req.getCustomerId());
        if (zohoTicketFromWhatsApp.isPresent()) {
            TicketUpdateResponse ticketUpdateResponse =
                    zohoClient.getTicket(String.valueOf(zohoTicketFromWhatsApp.get().getTicketId()));
            if (ticketUpdateResponse.getStatus()
                    .equalsIgnoreCase(zohoTicketFromWhatsApp.get().getTicketStatus())) {
                TicketCreationRequest tcRequest = TicketCreationRequest.builder().subject("WhatsAppChat")
                        .departmentId(KIAL_DEPARTMENT_ID).contactId(ticketUpdateResponse.getContactId())
                        // .description(ticketUpdateResponse.getDescription() + req.getIssueDescription())
                        .status(req.getZohoStatus()).channel(req.getChannel())
                        .cf(DeskContactCreationCustomFields.builder()
                                .cf_ieco_id(String.valueOf(req.getCustomerId()))
                                .cf_kotak_360_id(String.valueOf(req.getCustomerId())).cf_creation_mode(AUTOMATIC)
                                .cf_product_type(req.getProductCategory()).cf_disposition(req.getDisposition())
                                .cf_notes(req.getNotes() != null && !req.getNotes().isEmpty() ? req.getNotes() : "")
                                .build())
                        .build();

                log.info("ticket update request {}", tcRequest);

                TicketUpdateResponse tcResponse = zohoClient.ticketUpdate(tcRequest,
                        String.valueOf(zohoTicketFromWhatsApp.get().getTicketId()));
                return ResponseObject.builder().message(SUCCESS).timeStamp(System.currentTimeMillis())
                        .ticketDetails(tcResponse.getTicketNumber()).ticketId(tcResponse.getId())
                        .status(OK).build();

            } else {
                return createTicketForWahtsAppChat(req);
            }

        } else {
            return createTicketForWahtsAppChat(req);
        }

    }


    @Async("asyncExecutor")
    public CompletableFuture<ClevertapEventsResponse> cleverTapEvent(
            @RequestBody ClevertapEventsRequest clevertapEventsRequest) {
        log.info("clevertapEventsRequest received req {}", clevertapEventsRequest);

        try {
            ClevertapEventsResponse responseEntity = cleverTapClient
                    .uploadCleverTapEvents(clevertapEventsRequest, cleverTapAccountId, cleverTapPasscode);
            return CompletableFuture.completedFuture(responseEntity);
        } catch (Exception e) {
            log.error("Exception in cleverTapEvent method {}", e.getLocalizedMessage());
            e.printStackTrace();
        }
        log.info("Something went wrong in cleverTapEvent");
        ClevertapEventsResponse clevertapEventsResponse = ClevertapEventsResponse.builder().build();
        return CompletableFuture.completedFuture(clevertapEventsResponse);
    }


    public ResponseObject createCallbackZohoTicket(CallbackRequest req) {


        log.info("request for ticket creation from callback {}", req);
        try {
            String queryParam = CF_KOTAK_360 + ":" + req.getCustomerId();
            log.info(TICKET_CREATE_SEARCHING_CONTACT_IN_ZOHO_QUERY_PARAM, queryParam);
            ResponseEntity<ContactDetailsResponse> searchContactRes =
                    zohoService.searchContact(CF_KOTAK_360, req.getCustomerId());
            log.info("Contact searching Response by customer ID status and Id {} {}",
                    searchContactRes.getStatusCodeValue(), req.getCustomerId());
            log.info(TICKET_CREATE_CONTACT_SEARCHING_BY_CUSTOMER_ID_RESPONSE, searchContactRes);

            if (searchContactRes.getStatusCodeValue() == 200) {
                log.info(CONTACT_FOUND_IN_ZOHO_TRYING_TO_CREATE_TICKET, req);

                TicketCreationRequest tcRequest = TicketCreationRequest.builder().subject(req.getSubject())
                        .departmentId("KIAL_DEPARTMENT_ID")
                        .contactId(searchContactRes.getBody().getContactResponse().get(0).getId())
                        .channel("callback")
                        .description(req.getDesc())
                        // .description(req.getIssueDescription()).channel(req.getChannel())
                        .cf(DeskContactCreationCustomFields.builder()
                                .cf_ieco_id(String.valueOf(req.getCustomerId()))
                                .cf_kotak_360_id(String.valueOf(req.getCustomerId())).cf_creation_mode(AUTOMATIC)
                                .cf_created_date(new SimpleDateFormat(YYYY_MM_DD).format(new Date()))
                                .build())
                        .build();

                log.info("ticket creation request for callback {}", tcRequest);
                TicketCreationResponse tcResponse = zohoClient.ticketCreate(tcRequest);

                log.info("Ticket ID for callback {}", tcResponse.getId());

                return ResponseObject.builder().message(SUCCESS).timeStamp(System.currentTimeMillis())
                        .ticketDetails(tcResponse.getTicketNumber()).ticketId(tcResponse.getId()).status(OK)
                        .build();

            } else {
                log.info("Contact ID not found in Zoho with the customer ID " + req.getCustomerId());

                return ResponseObject.builder()
                        .message("Contact not found in zoho with the customer ID " + req.getCustomerId())
                        .timeStamp(System.currentTimeMillis()).status(CONTACT_NOT_FOUND_IN_ZOHO).build();

            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseObject.builder()
                    .message("Exception occured for ticket creation from callback " + req.getCustomerId())
                    .timeStamp(System.currentTimeMillis()).status("500 Internal Server Error").build();
        }

    }

}