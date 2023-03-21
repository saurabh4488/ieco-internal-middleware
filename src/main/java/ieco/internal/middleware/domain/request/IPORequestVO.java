package ieco.internal.middleware.domain.request;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * IPORequestVO.
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
 * Instantiates a new IPO request VO.
 *
 * @param userId the user id
 * @param clientId the client id
 */
@AllArgsConstructor

/**
 * Instantiates a new IPO request VO.
 */
@NoArgsConstructor
@ApiModel(description = "Request of IPORequestVO Api")
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IPORequestVO implements Serializable {
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -3162488040335670701L;

    /** The user id. */
    @JsonAlias("userId")
    private String userId;

    /** The client id. */
    @JsonAlias("clientId")
    private String clientId;

}
