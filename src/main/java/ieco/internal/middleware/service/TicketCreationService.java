package ieco.internal.middleware.service;


import ieco.internal.middleware.domain.request.CallbackRequest;
import ieco.internal.middleware.domain.request.MiddlewareRequest;
import ieco.internal.middleware.domain.request.WhatsAppChatRequest;
import ieco.internal.middleware.domain.response.ResponseObject;

public interface TicketCreationService {

    public ResponseObject ticketCreate(MiddlewareRequest middlewareRequest);

    public ResponseObject getZohoTicketDetails(WhatsAppChatRequest middlewareRequest);


    public ResponseObject createCallbackZohoTicket(CallbackRequest req);
}
