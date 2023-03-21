package ieco.internal.middleware.domain.request;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KsecCRMMasterRequest {

	private int flag;
	
	private String employeeId;
}
