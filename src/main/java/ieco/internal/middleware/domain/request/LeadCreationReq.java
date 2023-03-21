package ieco.internal.middleware.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class LeadCreationReq {

	private String Company;
	
	private String Last_Name;
	
	private String First_Name;
	
	private String Email;
	
	private String State;
	
	private String Phone;
	
	private String Customer_Type;
	
	private String Layout;
}
