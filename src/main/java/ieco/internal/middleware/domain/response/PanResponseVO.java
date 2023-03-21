package ieco.internal.middleware.domain.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PanResponseVO {

	private String panNumber;
	
	private String name;
	
	private String dob;
	
	private String document;
}
