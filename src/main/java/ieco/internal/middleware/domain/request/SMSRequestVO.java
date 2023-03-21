
package ieco.internal.middleware.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Instantiates a new SMS request VO.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SMSRequestVO  implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The msg req ID. */
	protected String msgReqID;

	/** The timestamp. */
	protected String timestamp;

	/** The src app cd. */
	protected String srcAppCd;

	/** The request ID. */
	protected String requestID;

	/** The sub app ID. */
	protected String subAppID;

	/** The priority. */
	protected String priority;

	/** The type. */
	protected String type;

	/** The to. */
	protected String to;

	/** The text. */
	protected String text;
	
	protected String contentTemplateId;
	protected String principalId;
}
