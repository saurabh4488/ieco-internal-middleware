package ieco.internal.middleware.domain.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MiddlewareRequest {

  private String otp;

  @Email
  private String email;

  @NotEmpty(message = "Mobile number should not be empty")
  @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
  @JsonAlias("phone")
  private String mobileNumber;

  @NotBlank(message = "Name is mandatory")
  private String name;

  private String sessionId;

  private int tempCustomerId;

  private String transactionId;

  private String productCategory;

  private String accessToken;

  private String issueDescription;

  @JsonAlias("IECO ID")
  private String customerId;

  private boolean satisfied;

  private String entity;

  private boolean isFromClevertap;

  private String channel;

  private String subCategory;
}
