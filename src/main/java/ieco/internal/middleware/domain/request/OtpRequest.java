package ieco.internal.middleware.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class OtpRequest implements Serializable {

	
	private static final long serialVersionUID = 1L;


	@JsonProperty("sessionLogId")
	
	private String sessionLogId;

	
	@JsonProperty("transactionId")
	
	
	private String transactionId;

	
	@JsonProperty("communicationTypeId")
	
	private int communicationTypeId;

	
	@JsonProperty("otpNumber")
	
	private String otpNumber;

	
	@JsonProperty("customerId")
	
	private Integer customerId;

	
	@JsonProperty("emailId")
	
	private String emailId;

	
	@JsonProperty("mobileNumber")
	
	private String mobileNumber;

}
