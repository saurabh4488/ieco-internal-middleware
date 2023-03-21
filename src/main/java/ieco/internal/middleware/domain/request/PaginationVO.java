package ieco.internal.middleware.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class PaginationVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotEmpty 
	private String pageNum;
	
	@NotEmpty 
	private String limit;
	
	@NotEmpty
	private String emailId;
	
	private String searchTerm;

}
