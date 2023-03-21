package ieco.internal.middleware.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class C2CInsertResponse {

private String Error_Code;
	
	private String Error_Description;
	
	private String RespCode;
	
	private String RescDesc;
}
