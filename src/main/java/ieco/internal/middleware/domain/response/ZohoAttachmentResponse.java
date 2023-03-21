package ieco.internal.middleware.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ZohoAttachmentResponse {
	
	private String id;
	
	private String name;
	
	private String size;
	
	private String createdTime;
	
	private String creatorId;
	
	private String isPublic;
	
	private String status;
	
	private String errorDesc;
	


}
