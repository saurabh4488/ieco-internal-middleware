package ieco.internal.middleware.domain.request;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class DigitalLandingStaticContentVO {

	
	private String cid;

	
	private String title;
	
	
	private String bulletPoints;
	
	
	private String formTitle;
	
	
	private String bgImageUrl;
	
	
	private String thankyouMessage;
	
	
	private String campaignName;
	
	
	private String offerText;
	
	
	private String offerDisclaimer;
	
	
	private String offerTagURL;
	
	
	
	private String layout;
	
	
	private String deepLinkingURL;

	private byte[] webPhoto;
	private byte[] mobPhoto;
	private String webImage;
	private String mobImage;
}
