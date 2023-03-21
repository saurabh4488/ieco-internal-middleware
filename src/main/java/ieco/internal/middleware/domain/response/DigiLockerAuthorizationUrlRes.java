package ieco.internal.middleware.domain.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DigiLockerAuthorizationUrlRes {

	private boolean success;
	private String authorizationEndpoint;

}
