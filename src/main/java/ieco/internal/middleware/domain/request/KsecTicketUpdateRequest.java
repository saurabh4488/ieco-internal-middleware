package ieco.internal.middleware.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class KsecTicketUpdateRequest {

	@NotBlank(message = "interactionId is mandatory")
   private String interactionId;
	@NotBlank(message = "status is mandatory")
    private String status;
	@NotBlank(message = "queryId is mandatory")
	
	private String queryId;
	@NotBlank(message = "remarks is mandatory")
	private String remarks;
	
	private String followUPUserId;
	
	private String followUpByName;
	
	private String followUpDatetime;
}
