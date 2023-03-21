package ieco.internal.middleware.domain.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ZohoTicketUpdateRequest {

	private String status;
	
	private String subject;
	
	private DeskContactCreationCustomFields cf;
	
}
