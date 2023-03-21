package ieco.internal.middleware.domain.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@ToString
public class CustomerDetailsRequest {

	private String customerEmail;
	
	private int customerId;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	/*@Override
	public String toString() {
		return "CustomerDetailsRequest [customerEmail=" + customerEmail + ", customerId=" + customerId + "]";
	}*/
	
}
