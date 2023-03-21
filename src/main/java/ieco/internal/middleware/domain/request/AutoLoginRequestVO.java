package ieco.internal.middleware.domain.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * AutoLoginRequestVO.
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
 * Instantiates a new auto login request VO.
 *
 * @param requestType the request type
 * @param data the data
 * @param timeStamp the time stamp
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@ApiModel(description = "Request of AutoLoginRequestVO")
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AutoLoginRequestVO implements Serializable{
/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8514005259480387678L;
	
	/** The request type. */
	@JsonProperty("RequestType")
    private String requestType;
	
	/** The data. */
	@JsonProperty("Data")
    private AutoLoginDataRequestVO data;
	
	/** The time stamp. */
	@JsonProperty("TimeStamp")
    private String timeStamp;

}
