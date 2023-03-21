package ieco.internal.middleware.domain.request;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder

public class LeadCreationData {

	private List<LeadCreationReq> data;
	
}
