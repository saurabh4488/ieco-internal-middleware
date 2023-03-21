package ieco.internal.middleware.controller;

import ieco.internal.middleware.domain.request.TPTicketUpdateRequest;
import ieco.internal.middleware.domain.response.ContactDetailsResponse;
import ieco.internal.middleware.domain.response.ResponseObject;
import ieco.internal.middleware.service.ZohoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//@CrossOrigin(origins = {"https://iecouat.kotakcherry.com", "http://localhost:4200"})
@RestController
public class ZohoController {

  @Value("${isZohoEnabled}")
  private boolean isZohoEnable;

  @Autowired
  private ZohoService zohoService;

  @GetMapping("/zoho/contacts/search")
  public ResponseEntity<ContactDetailsResponse> searchContact(
      @RequestParam("searchParameter") String searchParameter,
      @RequestParam("searchValue") String searchValue) {
    return zohoService.searchContact(searchParameter, searchValue);
  }

  @PostMapping("/tp/tickets/update")
  public ResponseObject updateTicketDetails(@RequestBody @Valid TPTicketUpdateRequest request) {
    if (true) {
      return zohoService.updateTicket(request);
    } else {
      ResponseObject res = new ResponseObject();

      res.setMessage("Updated successfully");
      res.setStatus("200 OK");
      res.setResponseCode("TP002");
      res.setTicketId(String.valueOf(request.getTicketId()));

      return res;
    }
  }
}
