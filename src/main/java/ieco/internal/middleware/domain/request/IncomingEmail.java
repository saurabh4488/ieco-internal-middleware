package ieco.internal.middleware.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class IncomingEmail implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotEmpty @Email
	private String email;

	private String name;
	
	private String mobile;
	
	private Boolean isSocialMedia;
	
	private String utmSource;
	
	private String utmMedium;
	
	private String utmCampaign;
	
	private String utmTerm;
	
	private String utmContent;
	
	private String password;
	
	private String source;
	
	private String medium;
	
	private String extras;
	
	private String invitationURL;
	
	private String tempCustomerId;
	
	private String cid;
}
