package ieco.internal.middleware.domain.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class InitiateTransactionRequest {

	private String sessionId;
	
	private String transactionType;
	
	private int tempCustomerId;
}
