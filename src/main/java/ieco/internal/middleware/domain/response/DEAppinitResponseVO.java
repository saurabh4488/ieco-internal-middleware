package ieco.internal.middleware.domain.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@JsonInclude(value=Include.NON_NULL)
public class DEAppinitResponseVO implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8595679565880916224L;
	
	/**  The type attribute. */
	private String type;
	
	/**  The public key attribute. */
	private String publicKey;
	
	/**  The key size attribute. */
	private int keySize;
	
	/**  The sessionId attribute. */
	private String sessionId;
	
	/**  The host attribute. */
	private String host;
	
	/**  The port attribute. */
	private String port;
	
	/**  The weblink host attribute. */
	private String weblinkhost;
	
	/**  The weblink port attribute. */
	private String weblinkport;
	
	/**  The service host attribute. */
	private String servicehost;
	
	/**  The service port attribute. */
	private String serviceport;

}
