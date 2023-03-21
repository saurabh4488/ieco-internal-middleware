package ieco.internal.middleware.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DigiLockerIssuedDocumentsRes {
	
	List<DigiLockerIssuedDocDetails> items;
	
	private String resource;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	 public static class DigiLockerIssuedDocDetails {
		 private String name;
		 private String type;
		 private String size;
		 private String date;
		 private String parent;
		 ArrayList < Object > mime = new ArrayList < Object > ();
		 private String uri;
		 private String doctype;
		 private String description;
		 private String issuerid;
		 private String issuer;


		
		}
	}