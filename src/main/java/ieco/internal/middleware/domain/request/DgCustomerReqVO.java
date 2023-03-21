package ieco.internal.middleware.domain.request;


import lombok.*;

import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class DgCustomerReqVO {

	
	private String iecoID;
	
	@Size(min=5,max=1000)
	private String code;
	
	private String clientId;
	
	private boolean isCelusion;
}
