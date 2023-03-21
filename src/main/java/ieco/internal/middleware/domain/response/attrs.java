package ieco.internal.middleware.domain.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Builder
@ToString
public class attrs {

	

	
	
	private userDetails userDetails;
	
}
