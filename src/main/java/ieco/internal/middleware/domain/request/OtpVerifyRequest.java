package ieco.internal.middleware.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
public class OtpVerifyRequest {

	@Setter
	@Getter
	private String tranId;
	
	@Setter
	@Getter
	private String otp;
}
