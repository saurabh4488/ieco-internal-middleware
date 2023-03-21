package ieco.internal.middleware.domain.response;


import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder
public class OtpResponse {
	
	private String timeStamp;
	
	private String status;
	
	private String responseCode;
	
	private String message;
	
	private attrs attrs;
	
	

	
	
}
