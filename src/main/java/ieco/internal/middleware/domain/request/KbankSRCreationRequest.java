package ieco.internal.middleware.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KbankSRCreationRequest {
	@NotBlank(message = "KMBEnterCRNType is mandatory")
	@JsonProperty("KMBEnterCRNType")
	private String crnType;
	
	@NotBlank(message = "KMBEnterCRN is mandatory")
	@JsonProperty("KMBEnterCRN")
	private String crn;
	
	
	@JsonProperty("kotak360Id")
	private String kotak360Id;
	
	@NotBlank(message = "Product is mandatory")
	@JsonProperty("Product")
	private String srProduct;
	
	
	@NotBlank(message = "Area is mandatory")
	@JsonProperty("Area")
	private String srType;
	
	@NotBlank(message = "SRType is mandatory")
	@JsonProperty("SRType")
	private String srNature;
	
	@NotBlank(message = "KMBCCGroup is mandatory")
	@JsonProperty("KMBCCGroup")
	private String srGroup;
	
	@NotBlank(message = "KMBSummary is mandatory")
	@JsonProperty("KMBSummary")
	private String summary;
	
	@NotBlank(message = "IntegrationId is mandatory")
	@JsonProperty("IntegrationId")
	private String ticketId;
	
	@NotBlank(message = "Priority is mandatory")
	@JsonProperty("Priority")
	private String priority;
	
	@NotBlank(message = "KotakInteractionChannel is mandatory")
	@JsonProperty("KotakInteractionChannel")
	private String kotakInteractionChannel;
	
	@NotBlank(message = "Source is mandatory")
	@JsonProperty("Source")
	private String source;

	@Override
	public String toString() {
		return "KbankSRCreationRequest [crnType=" + crnType + ", crn=" + crn + ", kotak360Id=" + kotak360Id
				+ ", srProduct=" + srProduct + ", srType=" + srType + ", srNature=" + srNature + ", srGroup=" + srGroup
				+ ", summary=" + summary + ", ticketId=" + ticketId + ", priority=" + priority
				+ ", kotakInteractionChannel=" + kotakInteractionChannel + ", source=" + source + "]";
	}
}
