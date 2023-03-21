package ieco.internal.middleware.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="CAMPAIGN_APP_STATIC_DATA")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SocialMediaContent {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator")
	@SequenceGenerator(name = "seq_generator", sequenceName = "SEQ_SOCIAL_MEDIA_CONTENT", allocationSize = 1)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="CID")
	private String cid;

	@Column(name="TITLE")
	private String title;
	
	@Column(name="BULLET_POINTS")
	private String bulletPoints;
	
	@Column(name="FORM_TITLE")
	private String formTitle;
	
	@Column(name="BGIMG_URL")
	private String bgImageUrl;
	
	@Column(name="THANKYOU_MESSAGE")
	private String thankyouMessage;
	
	@Column(name="CAMPAIGN_NAME")
	private String campaignName;
	
	@Column(name="OFFER_TEXT")
	private String offerText;
	
	@Column(name="OFFER_DISCLAIMER")
	private String offerDisclaimer;
	
	@Column(name="OFFER_TAG_URL")
	private String offerTagURL;
	
	@Column(name="CREATED_ON")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	
	@Column(name="LAYOUT")
	private String layout;
	
	@Column(name="DEEP_LINKING_URL")
	private String deepLinkingURL;
	
	@Column(name="IS_ACTIVE")
	private String isActive;

	@Lob
	@Column(name = "WEB_PHOTO", columnDefinition="BLOB")
	private byte[] webPhoto;

	@Lob
	@Column(name = "MOB_PHOTO", columnDefinition="BLOB")
	private byte[] mobPhoto;

	@Transient
	private String webImage;
	@Transient
	private String mobImage;
	
	
}
