package ieco.internal.middleware.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class ProvideAccessVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	private List<String> emailList;
	
	@NotEmpty
	private String userId;
	
	private String minimizeNumber;
	
}
