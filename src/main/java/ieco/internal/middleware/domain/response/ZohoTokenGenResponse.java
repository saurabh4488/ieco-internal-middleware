package ieco.internal.middleware.domain.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ZohoTokenGenResponse {

	private String access_token;
	
	private String api_domain;
	
	private String token_type;
	
	private int expires_in;
	
	private String error;

}
