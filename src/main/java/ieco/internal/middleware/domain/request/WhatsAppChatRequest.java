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
  // @Pattern(regexp = "Onboarding Completed|Will do it on own|Did not pick up|Number Not
  // Reachable/Switched Off|Follow up|Issues|Do Not Disturb|Not Interested|Not Interested - Using
  // other|Not Interested- Monetary Issue|Other Miscellaneous|Transaction Executed|Issue
  // Resolved|OTM Registered",flags = Pattern.Flag.CASE_INSENSITIVE, message="Invalid disposition
  // value")
  private String disposition;

  private String notes;

  @NotBlank(message = "channel is mandatory")
  private String channel;

  @NotBlank(message = "ZohoStatus is mandatory")
  private String zohoStatus;

}
