package ieco.internal.middleware.domain.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class TPTicketUpdateRequest {

	@NotNull(message="firstCallDate must not be null")
	private String firstCallDate;
	
	@NotNull(message="connectedDate must not be null")
	private String connectedDate;
	
	@Pattern(regexp = "Onboarding Completed|Will do it on own|Did not pick up|Number Not Reachable/Switched Off|Follow up|Issues|Do Not Disturb|Not Interested|Not Interested - Using other|Not Interested- Monetary Issue|Other Miscellaneous|Transaction Executed|Issue Resolved|OTM Registered",flags = Pattern.Flag.CASE_INSENSITIVE, message="Invalid disposition value")
	private String disposition;
	
	@NotNull(message="attempt must not be null")
	private Integer attempt;
	
	@Pattern(regexp = "Android App|Browser|IOS App", flags = Pattern.Flag.CASE_INSENSITIVE, message="platformType is must be web or app")
	private String platformType;
	
	@NotNull(message="remarks must not be null")
	private String remarks;
	
	//@NotNull(message="iecoId must not be null")
	private Integer customerId;
	
	@NotNull(message="email must not be null")
	private String email;
	
	@NotNull(message="ticketId must not be null")
	private long ticketId;

}
