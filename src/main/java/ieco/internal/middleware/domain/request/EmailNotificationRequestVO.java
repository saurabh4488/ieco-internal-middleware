package ieco.internal.middleware.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "Request for Email Notification")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EmailNotificationRequestVO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The email.
	 */
	@JsonProperty("from")
	@ApiModelProperty(position = 1, value = "from")
	private String from;

	/**
	 * The dob.
	 */
	@JsonProperty("to")
	@ApiModelProperty(position = 2, value = "to")
	private String to;

	/**
	 * The pan.
	 */
	@JsonProperty("subject")
	@ApiModelProperty(position = 3, value = "subject")
	private String subject;

	/**
	 * The transaction type.
	 */
	@JsonProperty("body")
	@ApiModelProperty(position = 4, value = "body")
	private String body; // forgot or locked
	
	/**
     * The attachment.
     */
    @JsonProperty("attachment")
    @ApiModelProperty(position = 5, value = "attachment")
    private Map<String, byte[]> attachment;

}
