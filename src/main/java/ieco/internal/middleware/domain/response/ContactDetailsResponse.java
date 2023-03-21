package ieco.internal.middleware.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@ToString
public class ContactDetailsResponse {
	private String errorCode;
	private String message;
	@JsonProperty("data")
	private List<ZohoContactCreationResponse> contactResponse;

	
}
