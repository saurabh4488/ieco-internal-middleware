package ieco.internal.middleware.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class TicketCreationRequest {

	private DeskContactCreationCustomFields cf;
	
	private String subCategory;
	
	private String productId;
	
	private String contactId;
	
	private String subject;
	
	private String dueDate;
	
	private String departmentId;
	
	private String channel;
	
	private String description;
	
	private String priority;
	
	private String classification;
	
	private String assigneeId;
	
	private String phone;
	
	private String category;
	
	private String email;
	
	private String status;


}
