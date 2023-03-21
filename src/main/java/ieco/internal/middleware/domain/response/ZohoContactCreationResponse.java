package ieco.internal.middleware.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import ieco.internal.middleware.domain.request.DeskContactCreationCustomFields;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
//@JsonInclude(JsonInclude.Include.NON_DEFAULT)

public class ZohoContactCreationResponse {

	private String lastName;
	
	private String id;
	
	private DeskContactCreationCustomFields cf;
	
	private String status;
	
	private String errMessage;
	
	private String type;
	
	private String mobile;
	
	private String email;
	
	private String firstName;
	private String errorCode;
	private String message;
	

	
}
