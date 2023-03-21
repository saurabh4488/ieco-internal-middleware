package ieco.internal.middleware.service;

import ieco.internal.middleware.domain.request.WhatsAppOTMRequest;
import ieco.internal.middleware.domain.response.ResponseObject;

public interface WhatsAppOTMService {

    ResponseObject sendWhatsAppOTMMessage(WhatsAppOTMRequest whatsAppOTMRequest);
}
