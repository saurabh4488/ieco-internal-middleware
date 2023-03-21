package ieco.internal.middleware.domain.response;

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
public class KbankSRCreationResponse {

	@JsonProperty("SRNumber")
	private String srNumber;
	
	@JsonProperty("Status")
	private String status;
	
	@JsonProperty("ReturnMessage")
	private String returnMessage;
	
	@JsonProperty("IntegrationId")
	private String ticketId;
	
	@JsonProperty("Error_spcMessage")
	private String errorMsg;
	
	
}
