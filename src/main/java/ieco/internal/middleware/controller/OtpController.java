package ieco.internal.middleware.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ieco.internal.middleware.domain.request.InitiateTransactionRequest;
import ieco.internal.middleware.domain.request.MiddlewareRequest;
import ieco.internal.middleware.domain.request.OtpRequest;
import ieco.internal.middleware.domain.request.RequestData;
import ieco.internal.middleware.domain.response.ResponseObject;
import ieco.internal.middleware.domain.response.ZohoAttachmentResponse;
import ieco.internal.middleware.service.impl.OtpService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Map;


//@CrossOrigin(origins = {"https://iecouat.kotakcherry.com", "http://localhost:4200"})
@RestController
public class OtpController {


  private org.slf4j.Logger log = LoggerFactory.getLogger(OtpController.class);

  @Autowired
  private OtpService otpService;

  @Value("${isZohoEnabled}")
  private boolean isZohoEnable;

  @PostMapping("/initiatetransaction")
  public ResponseEntity<ResponseObject> initiateTransaction(
      @RequestBody InitiateTransactionRequest req) {

    return otpService.initiateTransaction(req);

  }

  @PostMapping({"/otp/generate"})
  public ResponseEntity<ResponseObject> otpGeneration(@RequestBody OtpRequest req) {

    return otpService.otpGenerate(req);

  }


  @PostMapping("/otp/resend")
  public ResponseEntity<ResponseObject> otpResend(@RequestBody OtpRequest req) {

    return otpService.otpResend(req);

  }


  @PostMapping("/otp/validate")

  public <T> T verifyOtp(@RequestBody Map<String, Object> requestBody)
      throws JsonMappingException, JsonProcessingException {
    final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
    log.info("Request Received in otp controller");
    if (requestBody.get("satisfied") != null) {

      final MiddlewareRequest middlewareRequest =
          mapper.convertValue(requestBody, MiddlewareRequest.class);
      return otpService.processRequest(middlewareRequest);
    } else {
      MiddlewareRequest middlewareRequest =
          mapper.convertValue(requestBody, MiddlewareRequest.class);
      middlewareRequest.setSatisfied(false);
      return otpService.processRequest(middlewareRequest);
    }



  }

  @PostMapping(value = "/zoho/ticket/attachment", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

  public ZohoAttachmentResponse handleFileUpload(@RequestParam("file") MultipartFile file,
      @RequestParam("ticketId") String ticketId) {
    log.info("Request Received for zoho attachment ");
    return otpService.uploadFile(file, ticketId);
  }

  @PostMapping("/zoho/pre/tickets")
  public <T> T createTicket(@RequestBody @Valid RequestData request)
      throws JsonMappingException, JsonProcessingException {
    if (isZohoEnable) {

      return otpService.createTicket(
          MiddlewareRequest.builder().customerId(request.getCustomerId()).email(request.getEmail())
              .issueDescription(request.getIssueDescription()).mobileNumber(request.getPhone())
              .name(request.getName()).productCategory(request.getProductCategory())
              .subCategory(request.getSubCategory()).build());
    } else {
      ResponseObject res = new ResponseObject();

      res.setMessage("created successfully");
      res.setStatus("200 OK");
      res.setResponseCode("TP002");
      // res.setTicketId(String.valueOf(request.getTicketId()));

      return (T) res;
    }
  }

}
