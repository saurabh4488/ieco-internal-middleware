package ieco.internal.middleware.domain.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class C2CLoginResponse {

	private String Token;
	
	private String Error_Code;
	
	private String Error_Description;
	
private String RespCode;
	
	private String RescDesc;
	
}
