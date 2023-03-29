package ieco.internal.middleware.service.impl;


import ieco.internal.middleware.domain.request.*;
import ieco.internal.middleware.domain.response.*;
import ieco.internal.middleware.enums.ResponseCodeEnum;
import ieco.internal.middleware.feignclient.ZohoClient;
import ieco.internal.middleware.model.TPTicketUpdateDetails;
import ieco.internal.middleware.repository.TPRepository;
import ieco.internal.middleware.service.HelperService;
import ieco.internal.middleware.service.ZohoService;
import org.apache.commons.beanutils.BeanUtils;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Service
public class ZohoServiceImpl extends AbstractResponse implements ZohoService {

    private org.slf4j.Logger log = LoggerFactory.getLogger(ZohoServiceImpl.class);

    @RestClient
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
                ResponseEntity<HashMap<String,String>> customerType = helperService.getCustomerType(customerTypeRequest);
                log.info("Customer Type  :{}", customerType);
                if (customerType != null && customerType.getBody() != null) {
                    log.info("Customer Type  :{}", customerType.getBody());
                    DeskContactCreationCustomFields cf = null;
                    if (contactCreationReq.getCf() != null) {
                        cf = contactCreationReq.getCf();
                    } else {
                        cf = new DeskContactCreationCustomFields();
                    }

                    if (customerType!=null && customerType.getBody()!=null){
                        HashMap<String, String> customerTypeResponseBody=customerType.getBody();
                        cf.setCf_cherry_bank_category(customerTypeResponseBody.toString());
                        contactCreationReq.setCf(cf);
                    }
                    else {
                        throw new NullPointerException("Customer Type found null ");
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            log.error("Exception in contactCreation for CustomerType : {}",exception.getLocalizedMessage());
        }
        ZohoContactCreationResponse res = zohoClient.contactCreate(contactCreationReq);

        return new ResponseEntity<>(res, HttpStatus.OK);


    }

    @Override
    public TicketCreationResponse createTicket(TicketCreationRequest req) {


        return zohoClient.ticketCreate(req);

    }

    @Override
    public ResponseObject updateTicket(TPTicketUpdateRequest request) throws InvocationTargetException, IllegalAccessException {


        TPTicketUpdateDetails ticketUpdateDetails = TPTicketUpdateDetails.builder().build();
        BeanUtils.copyProperties(request, ticketUpdateDetails);
        ticketUpdateDetails.setIsPushedToZoho("N");
        tpRepository.save(ticketUpdateDetails);


        return responseSuccess("Updated successfully", ResponseCodeEnum.DETAILS_UPADTED_SUCCESSFULLY, String.valueOf(request.getTicketId()));


    }


}