package ieco.internal.middleware.domain.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class WhatsAppPushMessageResponse {

@JsonProperty("response")
public Response response;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public static class Response {

@JsonProperty("id")
public String id;
@JsonProperty("phone")
public String phone;
@JsonProperty("details")
public String details;
@JsonProperty("status")
public String status;

}
}