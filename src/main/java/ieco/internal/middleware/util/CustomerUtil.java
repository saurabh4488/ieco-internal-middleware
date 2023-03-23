package ieco.internal.middleware.util;

import ieco.internal.middleware.domain.request.CleverTapRequestVO;
import ieco.internal.middleware.domain.request.CustomerDetailsRequest;
import ieco.internal.middleware.domain.request.WhatsAppMediaRequest;
import ieco.internal.middleware.domain.response.CustomerDetailsResponse;
import ieco.internal.middleware.feignclient.PwcCustomerClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CustomerUtil {

  public static final String IECO_ID_RECEIVED_FROM_CLEVERTAP_IS_NULL = "IECO ID received from clevertap is null ";
  public static final String CUSTOMER_DETAILS_RESPONSE_FROM_PW_C = "CustomerDetailsResponse from PwC {}";
  private Logger log = LoggerFactory.getLogger(CustomerUtil.class);

  @Autowired
  private PwcCustomerClient iecoCustomerClient;

  public CustomerDetailsResponse getCustomerId(CleverTapRequestVO creq) {
    String custId = creq.getProfiles().get(0).getKeyValues().getCustomerId();
    CustomerDetailsRequest customerDetailsReq = new CustomerDetailsRequest();
    if (StringUtils.isEmpty(custId) || custId.equals("-1")) {
      log.info(IECO_ID_RECEIVED_FROM_CLEVERTAP_IS_NULL);

      customerDetailsReq.setCustomerEmail(creq.getProfiles().get(0).getEmail());
    } else {
      customerDetailsReq.setCustomerId(Integer.parseInt(custId));
    }

    ResponseEntity<CustomerDetailsResponse> custDetailsResEntity =
        iecoCustomerClient.getCustomerDetails(customerDetailsReq);

    CustomerDetailsResponse custDetailsRes = custDetailsResEntity.getBody();
    log.info(CUSTOMER_DETAILS_RESPONSE_FROM_PW_C, custDetailsRes);
    return custDetailsRes;
  }

  public CustomerDetailsResponse getCustomerId(WhatsAppMediaRequest creq) {
    String custId = creq.getProfiles().get(0).getKeyValues().getCustomerId();
    CustomerDetailsRequest customerDetailsReq = new CustomerDetailsRequest();
    if (StringUtils.isEmpty(custId) || custId.equals("-1")) {
      log.info(IECO_ID_RECEIVED_FROM_CLEVERTAP_IS_NULL);

      customerDetailsReq.setCustomerEmail(creq.getProfiles().get(0).getEmail());
    } else {
      customerDetailsReq.setCustomerId(Integer.parseInt(custId));
    }

    ResponseEntity<CustomerDetailsResponse> custDetailsResEntity =
            iecoCustomerClient.getCustomerDetails(customerDetailsReq);

    CustomerDetailsResponse custDetailsRes = custDetailsResEntity.getBody();
    log.info(CUSTOMER_DETAILS_RESPONSE_FROM_PW_C, custDetailsRes);
    return custDetailsRes;
  }

  public Long getCustomerId(String email) {

    CustomerDetailsRequest customerDetailsReq = new CustomerDetailsRequest();

    customerDetailsReq.setCustomerEmail(email);

    ResponseEntity<CustomerDetailsResponse> custDetailsResEntity =
        iecoCustomerClient.getCustomerDetails(customerDetailsReq);

    CustomerDetailsResponse custDetailsRes = custDetailsResEntity.getBody();
    log.info(CUSTOMER_DETAILS_RESPONSE_FROM_PW_C, custDetailsRes);
    return custDetailsRes.getAttrs().getUserDetails().getCustomerId();
  }

  public CustomerDetailsResponse getCustomerId(String email, String customerId) {

    CustomerDetailsRequest customerDetailsReq = new CustomerDetailsRequest();
    if (StringUtils.isEmpty(customerId) || customerId.equals("-1")) {
      log.info(IECO_ID_RECEIVED_FROM_CLEVERTAP_IS_NULL);

      customerDetailsReq.setCustomerEmail(email);
    } else {
      customerDetailsReq.setCustomerId(Integer.parseInt(customerId));
    }

    ResponseEntity<CustomerDetailsResponse> custDetailsResEntity =
        iecoCustomerClient.getCustomerDetails(customerDetailsReq);

    CustomerDetailsResponse custDetailsRes = custDetailsResEntity.getBody();
    log.info(CUSTOMER_DETAILS_RESPONSE_FROM_PW_C, custDetailsRes);
    return custDetailsRes;
  }
}
