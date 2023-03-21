package ieco.internal.middleware.controller;

import ieco.internal.middleware.domain.request.WhatsAppOTMRequest;
import ieco.internal.middleware.domain.response.AbstractResponse;
import ieco.internal.middleware.domain.response.ResponseObject;
import ieco.internal.middleware.enums.ResponseCodeEnum;
import ieco.internal.middleware.service.WhatsAppOTMService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class WhatsAppController extends AbstractResponse {

    @Autowired
    private WhatsAppOTMService whatsAppOTMService;

    @PostMapping(value = "/send-whatsapp-otm")
    public ResponseEntity<ResponseObject> sendWhatsAppOTM(@RequestBody WhatsAppOTMRequest whatsAppOTMRequest) {
        try {
            ResponseObject responseObject = whatsAppOTMService.sendWhatsAppOTMMessage(whatsAppOTMRequest);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        ResponseObject responseObject = responseError("Error", ResponseCodeEnum.WHATSAPP_OTM_FAILURE);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}
