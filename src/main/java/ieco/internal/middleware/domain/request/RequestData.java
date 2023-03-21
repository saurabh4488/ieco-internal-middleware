package ieco.internal.middleware.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Data
public class RequestData {

  private String customerId;
  @Email
  @NotEmpty(message = "Email should not be empty")
  private String email;
  private String issueDescription;
  private String name;
  @NotEmpty(message = "Phone number should not be empty")
  @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
  private String phone;
  private String productCategory;
  private String subCategory;
}
