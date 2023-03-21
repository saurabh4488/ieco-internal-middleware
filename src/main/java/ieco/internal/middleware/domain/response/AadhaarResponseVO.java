package ieco.internal.middleware.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AadhaarResponseVO {
	 
	AadhaarData AadhaarDataObject;
	private String xmlGeneratedDate;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class AadhaarData {
	 private String aadhaarNumber;
	 private String name;
	 private String dob;
	 private String gender;
	 private String photo;
	 private String document;
	 private String address;
	 private Poa PoaObject;
	 @JsonIgnore
	 private String timeStamp;
	 @JsonIgnore
	 private String txn;
	 
private String aadhaarXML;
	 @Data
	 @NoArgsConstructor
	 @AllArgsConstructor
	 @Builder
	public static class Poa {
	 private String careof;
	 private String country;
	 private String dist;
	 private String house;
	 private String landmark;
	 private String loc;
	 private String pc;
	 private String po;
	 private String state;
	 private String street;
	 private String subdist;
	 private String vtc;


	
	}

	
	}
	
}