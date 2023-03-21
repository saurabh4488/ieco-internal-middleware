package ieco.internal.middleware.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@ToString
public class CustomerDetailsResponse {

	private long timestamp;
	
	private String status;
	
	private String responseCode;
	private String message;
	private attrs attrs;
}
