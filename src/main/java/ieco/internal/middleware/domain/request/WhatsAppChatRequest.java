package ieco.internal.middleware.domain.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class WhatsAppChatRequest {

  @NotBlank(message = "Product Category is mandatory")
  private String productCategory;
  @JsonAlias("iecoId")
  @NotBlank(message = "IECO ID is mandatory")
  private String customerId;

  @NotBlank(message = "Disposition is mandatory")
  private String disposition;

  private String notes;

  @NotBlank(message = "channel is mandatory")
  private String channel;

  @NotBlank(message = "ZohoStatus is mandatory")
  private String zohoStatus;

}
