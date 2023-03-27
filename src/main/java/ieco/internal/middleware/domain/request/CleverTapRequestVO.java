package ieco.internal.middleware.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CleverTapRequestVO {
	@JsonProperty("profiles")
	private List<Profile> profiles;

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@ToString
	public static class Profile {

		@JsonProperty("key_values")
		private KeyValues keyValues;
		@JsonProperty("email")
		private String email;
		@JsonProperty("identity")
		private String identity;
		@JsonProperty("objectId")
		private String objectId;
		@JsonProperty("name")
		private String name;
		
	}

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@ToString
		public static class KeyValues {

		@JsonProperty("customerId")
		private String customerId;
		
		@JsonProperty("productCategory")
		private String productCategory;
		
		@JsonProperty("issueDescription")
		private String issueDescription;
		
		@JsonProperty("enablesms")
		private String enableSMS="N";
		
		@JsonProperty("page")
		private String page;
		
		@JsonProperty("enablewhatsapp")
		private String enableWhatsApp="N";
		 
		@JsonProperty("smsmessage")
		private String smsMessage;
		
		@JsonProperty("whatsappmessage")
		private String whatsAppMessage;
		
		@JsonProperty("smstemplate")
		private String smsTemplate;
		
		@JsonProperty("params")
		private String params;
		
		@JsonProperty("url")
		private String url;

		@JsonProperty("whatsappsms")
		private String whatsappsms;

		@JsonProperty("mobile")
		private String mobile;

		@JsonProperty("whatsappTemplateId")
		private String whatsappTemplateId;

		@JsonProperty("isTemplate")
		private boolean isTemplate=false;
	}
}
