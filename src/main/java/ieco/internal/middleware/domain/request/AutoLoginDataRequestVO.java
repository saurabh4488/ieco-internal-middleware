package ieco.internal.middleware.domain.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * AutoLoginDataRequestVO.
 */

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Builder

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data

/**
 * Instantiates a new auto login data request VO.
 *
 * @param userId the user id
 * @param token the token
 * @param linkId the link id
 * @param appSource the app source
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(description = "Request of AutoLoginDataRequestVO Details")
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AutoLoginDataRequestVO implements Serializable{
/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 561254354325897767L;
	
	/** The user id. */
	@JsonProperty("UserId")
	private String userId;
	
	/** The token. */
	@JsonProperty("Token")
	private String token;
	
	/** The link id. */
	@JsonProperty("LinkId")
	private Integer linkId;
	
	/** The app source. */
	@JsonProperty("AppSource")
	private Integer appSource;
    
}
