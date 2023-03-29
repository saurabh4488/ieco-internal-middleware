package ieco.internal.middleware.feignclient;

import ieco.internal.middleware.domain.request.OtpRequest;
import ieco.internal.middleware.domain.response.ResponseObject;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RegisterRestClient
public interface PwcOtpClient {
	
	@PostMapping("/v1/otp/validate")
	ResponseEntity<ResponseObject> otpVerify(@RequestBody OtpRequest req);
		
	@PostMapping("/v1/otp/generate")
	ResponseEntity<ResponseObject> otpGenerate(@RequestBody OtpRequest req);
	
	@PostMapping("/v1/otp/resend")
	ResponseEntity<ResponseObject> otpResend(@RequestBody OtpRequest req);

}
