package ieco.internal.middleware.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DigiLockerAccessTokenRes {
	
	private String access_token;
	 private float expires_in;
	 private String token_type;
	 private String scope;
	 private String refresh_token;
	 private String digilockerid;
	 private String name;
	 private String dob;
	 private String gender;
	 private String eaadhaar;
	 private String reference_key;
	 private String new_account;


	 
	}