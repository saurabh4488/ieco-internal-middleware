package ieco.internal.middleware.domain.request;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class Click2CallLogin {

	private String Login_ID;
	
	private String Login_Pwd;
	
	private String Client_IP;
	
	private String Channel_ID;
}