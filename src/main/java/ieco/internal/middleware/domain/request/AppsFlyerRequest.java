package ieco.internal.middleware.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AppsFlyerRequest {

    @JsonProperty("appsflyer_id")
    private String appsflyer_id;
    @JsonProperty("advertising_id")
    private String advertising_id;
    @JsonProperty("customer_user_id")
    private String customer_user_id;
    @JsonProperty("ip")
    private String ip;
    @JsonProperty("app_version_name")
    private String app_version_name;
    @JsonProperty("eventTime")
    private String eventTime;
    @JsonProperty("eventName")
    private String eventName;
    @JsonProperty("eventCurrency")
    private String eventCurrency;
    @JsonProperty("eventValue")
    private EventValue eventValue;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class EventValue {
        @JsonProperty("af_revenue")
        private String af_revenue;
        @JsonProperty("af_content_type")
        private String af_content_type;
        @JsonProperty("af_content_id")
        private String af_content_id;
        @JsonProperty("af_quantity")
        private String af_quantity;
    }

}
