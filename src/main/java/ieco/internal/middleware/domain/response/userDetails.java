package ieco.internal.middleware.domain.response;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder
@ToString
//@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class userDetails {

	private String fname;
	
	private String lname;
	
	private String mobile;
	
	private String email;
	
	private long customerId;
	
	private String bankCrn;
	 
     private String ksecClientCode;
}
