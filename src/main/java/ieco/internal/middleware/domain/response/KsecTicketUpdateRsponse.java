package ieco.internal.middleware.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)

public class KsecTicketUpdateRsponse {

	
	private String status;
	
	private String interActionId;
	
	private String querId;
	
	private String modifiedTime;
	
}
