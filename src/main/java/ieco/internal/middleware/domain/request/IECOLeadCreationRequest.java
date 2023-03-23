package ieco.internal.middleware.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class IECOLeadCreationRequest {

	@NotBlank(message = "email is mandatory")
	private String email;
	
	@NotBlank(message = "mobile number is mandatory")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
	private String mobile;
	
	//@NotBlank(message = "lastName is mandatory")
	private String lastName;
	
	//@NotBlank(message = "firstName is mandatory")
	private String firstName;
	
	@NotBlank(message = "stage is mandatory")
	private String stage;
	
	@NotBlank(message = "CustomerId is mandatory")
	private String customerId;
	
	private String crnCreated;
	
	private String clientCodeCreated;
	
	private String environment;
	
	private List<Object> productsPurchased;
	
	// for social media content
    private String utmSource;
	
	private String utmMedium;
	
	private String utmCampaign;
	
	private String utmTerm;
	
	private String utmContent;
	
	@JsonProperty("distributionFlag")
	private boolean distributionFlag;
	
	//@NotBlank(message = "Gender is mandatory")
	private String gender;
}
