package ieco.internal.middleware.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Data
public class WaitingListDetailsVO {

	@JsonProperty(required = true)
	private String emailId;
	
	
	private String mobileNumber;
	
	
	private String fullName;
	
	
	private String referenceCode;
	
	
	private String linkedinURL;
	
	
	private String isAccessProvided;
	
	
	private String referedByCode;
	
	
	private String referedByEmail;
	
	
	private String check1;
	
	
	private String check2;
	
	private Object waitingListNumber;
	
	private Object offSet;
	
	private boolean isFirstTime;
	
	private String source;
	
	private String medium;
	
	private String extras;
	
	private String tempCustomerId;
	
	
	
}
