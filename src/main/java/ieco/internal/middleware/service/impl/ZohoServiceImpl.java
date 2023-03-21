package ieco.internal.middleware.service.impl;


import ieco.internal.middleware.domain.request.*;
import ieco.internal.middleware.domain.response.*;
import ieco.internal.middleware.enums.ResponseCodeEnum;
import ieco.internal.middleware.feignclient.ZohoClient;
import ieco.internal.middleware.model.TPTicketUpdateDetails;
import ieco.internal.middleware.repository.TPRepository;
import ieco.internal.middleware.service.HelperService;
import ieco.internal.middleware.service.ZohoService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ZohoServiceImpl extends AbstractResponse implements ZohoService {

    private org.slf4j.Logger log = LoggerFactory.getLogger(ZohoServiceImpl.class);

    @Autowired
    private ZohoClient zohoClient;


    @Autowired
    private TPRepository tpRepository;

    @Autowired
    private HelperService helperService;

    static String formatDate(String format) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

        Date date;
        try {
            date = sdf.parse(format);
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public ResponseEntity<ContactDetailsResponse> searchContact(String searchParameter, String searchValue) {


        String queryParam = searchParameter + ":" + searchValue;

        log.info("Searching contact in zoho searchValue:{}", searchValue);
        log.info("Searching contact in zoho searchParameter:{}", searchParameter);
        log.info("Searching contact in zoho queryParam :{}", queryParam);

        ResponseEntity<ContactDetailsResponse> responseEntity = zohoClient.searchContact(queryParam);

        return responseEntity;
    }

    @Override
    public ResponseEntity<ZohoContactCreationResponse> contactCreation(DeskContactCreationRequest contactCreationReq) {
        try {
            CustomerTypeRequest customerTypeRequest = new CustomerTypeRequest();
            if (contactCreationReq.getEmail() != null) {
                customerTypeRequest.setEmailId(contactCreationReq.getEmail());
                ResponseEntity customerType = helperService.getCustomerType(customerTypeRequest);
                log.info("Customer Type  :{}", customerType);
                if (customerType != null && customerType.getBody() != null) {
                    log.info("Customer Type  :{}", customerType.getBody().toString());
                    DeskContactCreationCustomFields cf = null;
                    if (contactCreationReq.getCf() != null) {
                        cf = contactCreationReq.getCf();
                    } else {
                        cf = new DeskContactCreationCustomFields();
                    }
                    cf.setCf_cherry_bank_category(customerType.getBody().toString());
                    contactCreationReq.setCf(cf);
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            log.error("Exception in contactCreation for CustomerType" + exception.getLocalizedMessage());
        }
        ZohoContactCreationResponse res = zohoClient.contactCreate(contactCreationReq);

        return new ResponseEntity<>(res, HttpStatus.OK);


    }

    @Override
    public TicketCreationResponse createTicket(TicketCreationRequest req) {


        return zohoClient.ticketCreate(req);

    }

    @Override
    public ResponseObject updateTicket(TPTicketUpdateRequest request) {


        TPTicketUpdateDetails ticketUpdateDetails = TPTicketUpdateDetails.builder().build();
        BeanUtils.copyProperties(request, ticketUpdateDetails);
        ticketUpdateDetails.setIsPushedToZoho("N");
        tpRepository.save(ticketUpdateDetails);
        TicketUpdateResponse ticketRes = zohoClient.getTicket(String.valueOf(request.getTicketId()));


        ZohoTicketUpdateRequest zohoTicketUpdateRequest = ZohoTicketUpdateRequest.builder()
                .cf(DeskContactCreationCustomFields.builder().cf_attempt(request.getAttempt())
                        .cf_connected_date(formatDate(request.getConnectedDate()))
                        .cf_first_call_date(formatDate(request.getFirstCallDate()))
                        .cf_device_type(request.getPlatformType())
                        .cf_disposition(request.getDisposition())
                        .cf_remarks(request.getRemarks())
                        .build()).build();
        TicketUpdateResponse tcResponse = zohoClient.ticketUpdate(zohoTicketUpdateRequest, String.valueOf(request.getTicketId()));
        return responseSuccess("Updated successfully", ResponseCodeEnum.DETAILS_UPADTED_SUCCESSFULLY, String.valueOf(request.getTicketId()));


    }


}