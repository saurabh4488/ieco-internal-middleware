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

    private static final String DEFAULT_DEPARTMENT = "KIAL";

    private static final String KIAL_DEPARTMENT_ID = "17634000000072420";

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

            /*
             * if (isZohoEnable && middlewareRequest.getCustomerId() != "-1" && null !=
             * middlewareRequest.getCustomerId()) {
             */
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
                resObject.setMessage("Success");
                resObject.setTimeStamp(System.currentTimeMillis());
                resObject.setTicketDetails("123");
                resObject.setTicketId("9876543");
                resObject.setStatus("200 OK");
                return resObject;
            }

        } catch (Exception e) {
            e.printStackTrace();
            res = new ResponseObject();
            res.setMessage("Error Occurred while creating Ticket-" + e.getCause());
            res.setStatus("500 Internal Server Error");
            log.info("Ticket creation Failed for request {}", middlewareRequest);
            log.error("Exception in ticketCreate {}", e);
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

        // DeskContactCreationCustomFields cf = new DeskContactCreationCustomFields();
        // cf.setCf_created_date(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        TicketUpdateResponse tcResponse = zohoClient.ticketUpdate(zohoTicketUpdateRequest, ticketId);
        ResponseObject resObject = new ResponseObject();
        resObject.setMessage("Success");
        resObject.setTimeStamp(System.currentTimeMillis());
        resObject.setTicketDetails(tcResponse.getTicketNumber());
        resObject.setTicketId(tcResponse.getId());
        resObject.setStatus("200 OK");
        return resObject;
    }

    private ResponseObject createTicket(MiddlewareRequest middlewareRequest) {

        ResponseEntity<ContactDetailsResponse> searchContactRes = null;
        log.info("request for ticket creation in post login {}", middlewareRequest);
        if (middlewareRequest.getCustomerId() != null && middlewareRequest.getCustomerId() != "") {
            String queryParam = "cf_kotak_360" + ":" + middlewareRequest.getCustomerId();
            log.info("ticketCreate--Searching contact in zoho queryParam :{}", queryParam);
            searchContactRes =
                    zohoService.searchContact("cf_kotak_360", middlewareRequest.getCustomerId());
            // ResponseEntity<ContactDetailsResponse> searchContactRes =
            // zohoClient.searchContact(queryParam);
            log.info("Post login Contact searching Response by customer ID status and Id {} {}",
                    searchContactRes.getStatusCodeValue(), middlewareRequest.getCustomerId());
            log.info("ticketCreate--Contact searching  by customer ID Response {}", searchContactRes);
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
            log.info("Contact Found in Zoho, trying to create Ticket {}", middlewareRequest);
            String contactId = searchContactRes.getBody().getContactResponse().get(0).getId();


            TicketCreationRequest tcRequest = new TicketCreationRequest();
            DeskContactCreationCustomFields cf = new DeskContactCreationCustomFields();
            cf.setCf_environment(environment);
            // String department = DepartmentUtil.getdepartment(middlewareRequest.getProductCategory());
            /*
             * if (department.equalsIgnoreCase("K-Sec")) { cf.setCf_replicate_to_ksec_crm("Yes");
             * cf.setCf_sr_nature(""); cf.setCf_send_sms_1("No"); cf.setCf_calling_done("Yes");
             * cf.setCf_remarks(middlewareRequest.getIssueDescription()); tcRequest.setPriority("High"); }
             * if (department.equalsIgnoreCase("K-Bank")) { cf.setCf_replicate_to_siebel("No");
             * cf.setCf_sr_nature("Request"); cf.setCf_sr_product("Retail Liabilities");
             * cf.setCf_send_email("Yes"); cf.setCf_summary(middlewareRequest.getIssueDescription());
             * tcRequest.setPriority("High"); } if (department.equalsIgnoreCase("KIAL")) {
             *
             * cf.setCf_sr_nature("FTR"); tcRequest.setPriority("Medium"); }
             */
            cf.setCf_sr_nature("FTR");
            cf.setCf_crn_type("Individual");
            cf.setCf_sr_group("");
            cf.setCf_ieco_id(middlewareRequest.getCustomerId());
            cf.setCf_kotak_360_id(middlewareRequest.getCustomerId());
            // cf.setCf_kotak_360_id(kotak360Id);
            cf.setCf_product_type(middlewareRequest.getProductCategory());

            cf.setCf_sr_classification("Service Request");
            cf.setCf_ticket_department(DEFAULT_DEPARTMENT);
            cf.setCf_creation_mode("Automatic");
            cf.setCf_created_date(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

            tcRequest.setEmail(middlewareRequest.getEmail());
            // tcRequest.setPhone(middlewareRequest.getMobileNumber());
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
                // tcRequest.setSubject("Aadhar Drop off Real time Call Clevertap");
                tcRequest.setPriority("High");
                tcRequest.setChannel("Clevertap");
                cf.setCf_customer_journey("On-boarding");
                if (middlewareRequest.getIssueDescription().equalsIgnoreCase("MF Cart Real Time Calling"))
                    cf.setCf_customer_journey("Transaction");
            }
            tcRequest.setCf(cf);
            log.info("ticket creation request {}", tcRequest);

            TicketCreationResponse tcResponse = zohoClient.ticketCreate(tcRequest);
            // log.info("Ticket Creation Success {}", middlewareRequest);
            log.info("Ticket ID is {}", tcResponse.getId());

            // log.info("Ticket creation response {}", tcResponse.getBody());

            storeInCache(tcResponse.getId(), middlewareRequest.getCustomerId(),
                    middlewareRequest.isFromClevertap());
            return ResponseObject.builder().message("Success").timeStamp(System.currentTimeMillis())
                    .ticketDetails(tcResponse.getTicketNumber()).ticketId(tcResponse.getId()).status("200 OK")
                    .build();

        } else {
            log.info("Contact ID not found in Zoho with the customer ID and Email ID "
                    + middlewareRequest.getCustomerId() + "-" + middlewareRequest.getEmail());

            return ResponseObject.builder()
                    .message("Contact not found in zoho with the customer ID and Email ID "
                            + middlewareRequest.getCustomerId() + "-" + middlewareRequest.getEmail())
                    .timeStamp(System.currentTimeMillis()).status("404 Contact not found in Zoho").build();
        }
    }

    private void storeInCache(String ticketId, String customerId, boolean isCleverTap) {
        log.info("ticket id storing in cache......");
        ZohoTicketCache ticketCache =
                ZohoTicketCache.builder().customerId(customerId).ticketId(ticketId).build();
        zohoUtility.storeTicketDetailsInCache(ticketCache);
    }

    public ResponseObject createTicketForWahtsAppChat(WhatsAppChatRequest req,
                                                      Optional<ZohoTicketsFromWhatsApp> zohoTicketFromWhatsApp) {

        log.info("request for ticket creation from whatsapp {}", req);

        String queryParam = "cf_kotak_360" + ":" + req.getCustomerId();
        log.info("ticketCreate--Searching contact in zoho queryParam :{}", queryParam);
        ResponseEntity<ContactDetailsResponse> searchContactRes =
                zohoService.searchContact("cf_kotak_360", req.getCustomerId());
        log.info("Contact searching Response by customer ID status and Id {} {}",
                searchContactRes.getStatusCodeValue(), req.getCustomerId());
        log.info("ticketCreate--Contact searching  by customer ID Response {}", searchContactRes);

        if (searchContactRes.getStatusCodeValue() == 200) {
            log.info("Contact Found in Zoho, trying to create Ticket {}", req);

            TicketCreationRequest tcRequest = TicketCreationRequest.builder().subject("WhatsAppChat")
                    .departmentId("17634000000072420")
                    .contactId(searchContactRes.getBody().getContactResponse().get(0).getId())
                    // .description(req.getIssueDescription()).channel(req.getChannel())
                    .status(req.getZohoStatus())
                    .channel(req.getChannel())
                    .cf(DeskContactCreationCustomFields.builder()
                            .cf_ieco_id(String.valueOf(req.getCustomerId()))
                            .cf_kotak_360_id(String.valueOf(req.getCustomerId())).cf_creation_mode("Automatic")
                            .cf_created_date(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
                            .cf_product_type(req.getProductCategory()).cf_disposition(req.getDisposition())
                            .cf_notes(req.getNotes() != null && !req.getNotes().isEmpty() ? req.getNotes() : "")
                            .build())
                    .build();

            log.info("ticket creation request {}", tcRequest);
            TicketCreationResponse tcResponse = zohoClient.ticketCreate(tcRequest);

            log.info("Ticket ID is {}", tcResponse.getId());
            /*CompletableFuture.runAsync(new Runnable() {
                @Override
                public void run() {
                    if (zohoTicketFromWhatsApp.isPresent()) {
                        ZohoTicketsFromWhatsApp zohoTicketsFromWhatsApp = zohoTicketFromWhatsApp.get();
                        zohoTicketsFromWhatsApp.setTicketId(Long.valueOf(tcResponse.getId()));
                        zohoTicketsFromWhatsApp.setTicketStatus(tcResponse.getStatus());
                        zohoTicketsFromWhatsApp.setCreatedOn(new Date());
                        zohoTicketsFromWhatsApp.setUpdatedOn(new Date());
                        whatsAppTicketRepository.save(zohoTicketsFromWhatsApp);
                    } else {
                        whatsAppTicketRepository
                                .save(ZohoTicketsFromWhatsApp.builder().customerId(req.getCustomerId())
                                        .ticketId(Long.valueOf(tcResponse.getId())).createdOn(new Date())
                                        .updatedOn(new Date()).ticketStatus(tcResponse.getStatus()).build());
                    }
                }
            });*/

            return ResponseObject.builder().message("Success").timeStamp(System.currentTimeMillis())
                    .ticketDetails(tcResponse.getTicketNumber()).ticketId(tcResponse.getId()).status("200 OK")
                    .build();

        } else {
            log.info("Contact ID not found in Zoho with the customer ID " + req.getCustomerId());

            return ResponseObject.builder()
                    .message("Contact not found in zoho with the customer ID " + req.getCustomerId())
                    .timeStamp(System.currentTimeMillis()).status("404 Contact not found in Zoho").build();

        }

    }

    public ResponseObject getZohoTicketDetails(WhatsAppChatRequest req) {
        log.info("WhatsAppChatRequest req {}", req);
        ResponseObject createWhatsAppChatResponseObject = createTicketForWahtsAppChat(req, null);
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
                        .departmentId("17634000000072420").contactId(ticketUpdateResponse.getContactId())
                        // .description(ticketUpdateResponse.getDescription() + req.getIssueDescription())
                        .status(req.getZohoStatus()).channel(req.getChannel())
                        .cf(DeskContactCreationCustomFields.builder()
                                .cf_ieco_id(String.valueOf(req.getCustomerId()))
                                .cf_kotak_360_id(String.valueOf(req.getCustomerId())).cf_creation_mode("Automatic")
                                .cf_product_type(req.getProductCategory()).cf_disposition(req.getDisposition())
                                .cf_notes(req.getNotes() != null && !req.getNotes().isEmpty() ? req.getNotes() : "")
                                .build())
                        .build();

                log.info("ticket update request {}", tcRequest);

                TicketUpdateResponse tcResponse = zohoClient.ticketUpdate(tcRequest,
                        String.valueOf(zohoTicketFromWhatsApp.get().getTicketId()));
                return ResponseObject.builder().message("Success").timeStamp(System.currentTimeMillis())
                        .ticketDetails(tcResponse.getTicketNumber()).ticketId(tcResponse.getId())
                        .status("200 OK").build();

            } else {
                return createTicketForWahtsAppChat(req, zohoTicketFromWhatsApp);
            }

        } else {
            return createTicketForWahtsAppChat(req, zohoTicketFromWhatsApp);
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
            String queryParam = "cf_kotak_360" + ":" + req.getCustomerId();
            log.info("ticketCreate--Searching contact in zoho queryParam :{}", queryParam);
            ResponseEntity<ContactDetailsResponse> searchContactRes =
                    zohoService.searchContact("cf_kotak_360", req.getCustomerId());
            log.info("Contact searching Response by customer ID status and Id {} {}",
                    searchContactRes.getStatusCodeValue(), req.getCustomerId());
            log.info("ticketCreate--Contact searching  by customer ID Response {}", searchContactRes);

            if (searchContactRes.getStatusCodeValue() == 200) {
                log.info("Contact Found in Zoho, trying to create Ticket {}", req);

                TicketCreationRequest tcRequest = TicketCreationRequest.builder().subject(req.getSubject())
                        .departmentId("17634000000072420")
                        .contactId(searchContactRes.getBody().getContactResponse().get(0).getId())
                        .channel("callback")
                        .description(req.getDesc())
                        // .description(req.getIssueDescription()).channel(req.getChannel())
                        .cf(DeskContactCreationCustomFields.builder()
                                .cf_ieco_id(String.valueOf(req.getCustomerId()))
                                .cf_kotak_360_id(String.valueOf(req.getCustomerId())).cf_creation_mode("Automatic")
                                .cf_created_date(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
                                .build())
                        .build();

                log.info("ticket creation request for callback {}", tcRequest);
                TicketCreationResponse tcResponse = zohoClient.ticketCreate(tcRequest);

                log.info("Ticket ID for callback {}", tcResponse.getId());

                return ResponseObject.builder().message("Success").timeStamp(System.currentTimeMillis())
                        .ticketDetails(tcResponse.getTicketNumber()).ticketId(tcResponse.getId()).status("200 OK")
                        .build();

            } else {
                log.info("Contact ID not found in Zoho with the customer ID " + req.getCustomerId());

                return ResponseObject.builder()
                        .message("Contact not found in zoho with the customer ID " + req.getCustomerId())
                        .timeStamp(System.currentTimeMillis()).status("404 Contact not found in Zoho").build();

            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseObject.builder()
                    .message("Exception occured for ticket creation from callback " + req.getCustomerId())
                    .timeStamp(System.currentTimeMillis()).status("500 Internal Server Error").build();
        }

    }

}