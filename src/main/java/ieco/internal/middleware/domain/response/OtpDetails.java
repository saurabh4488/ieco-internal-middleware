package ieco.internal.middleware.domain.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class OtpDetails {

	public OtpDetails(boolean userBlock, int otpremainingCount) {
		super();
		this.userBlock = userBlock;
		this.otpremainingCount = otpremainingCount;
	}

	private boolean userBlock;
	
	private int otpremainingCount;
}
