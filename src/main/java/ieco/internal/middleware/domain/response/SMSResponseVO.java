package ieco.internal.middleware.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * The Class SMSResponseVO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SMSResponseVO implements Serializable {
    
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The from. */
	protected String from;
    
    /** The to number. */
    protected String toNumber;
    
    /** The msg txt. */
    protected String msgTxt;
    
    /** The status code. */
    protected String statusCode;
    
    /** The status desc. */
    protected String statusDesc;
    
    /** The acceptance time. */
    protected String acceptanceTime;
}
