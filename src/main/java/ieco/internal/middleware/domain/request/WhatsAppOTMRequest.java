package ieco.internal.middleware.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class WhatsAppOTMRequest {
    private String customerId;
    private String customerName;
    private double sipAmount;
    private String schemeName;
    private String deepLink;
    private boolean isTemplate;
    private String whatsAppMessage;
    private String mobile;
    private String mediaUrl;
}
