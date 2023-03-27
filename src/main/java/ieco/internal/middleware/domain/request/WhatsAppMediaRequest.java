package ieco.internal.middleware.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class WhatsAppMediaRequest {


  @JsonProperty("profiles")
  private List<Profile> profiles;

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  @ToString
    public static class Profile {

    @JsonProperty("key_values")
    private KeyValues keyValues;
    @JsonProperty("email")
    private String email;
    @JsonProperty("identity")
    private String identity;
    @JsonProperty("objectId")
    private String objectId;
    @JsonProperty("name")
    private String name;

  }

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  @ToString
    public static class KeyValues {

    // @NotBlank(message = "customerId is mandatory")
    private String customerId;

    // @NotBlank(message = "email is mandatory")
    private String email;

    private String params;

    private String url;

    @NotBlank(message = "mediaURL is mandatory")
    private String mediaURL;

    @NotBlank(message = "whatsAppSms is mandatory")
    private String whatsAppSms;

    private String mobile;

    private String footer;
  }




}
