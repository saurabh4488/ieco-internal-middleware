package ieco.internal.middleware.domain.request;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
@JsonPropertyOrder({"Channel_ID", "Crm_Ticket_ID", "Customer_ID", "Channel_IP", "First_Name", "Last_Name","Mobile_Number","Product_Category","Entity","Issue_Description","Reserve1","Reserve2","Reserve3","Reserve4","Reserve5"})
public class C2CInsertDetails {

	//@NotBlank(message = "Channel_ID is mandatory")
	private String Channel_ID;
	
	@NotBlank(message = "Crm_Ticket_ID is mandatory")
	private String Crm_Ticket_ID;
	
	@NotBlank(message = "Customer_ID is mandatory")
	private String Customer_ID;
	
	@NotBlank(message = "Channel_IP is mandatory")
	private String Channel_IP;
	
	@NotBlank(message = "First_Name is mandatory")
	private String First_Name;
	
	@NotBlank(message = "Last_Name is mandatory")
	private String Last_Name;
	
	private String Mobile_Number;
	
	@NotBlank(message = "Product_Category is mandatory")
	private String Product_Category;
	
	@NotBlank(message = "Entity is mandatory")
	private String Entity;
	
	@NotBlank(message = "Issue_Description is mandatory")
	private String Issue_Description;
	
	private String Reserve1;
	
	private String Reserve2;
	
	private String Reserve3;
	
	private String Reserve4;
	
	private String Reserve5;
}
