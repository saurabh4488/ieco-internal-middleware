package ieco.internal.middleware.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="CUSTOMERS_FROM_CAMPAIGN")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UsersFromSocialMedia {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator")
	@SequenceGenerator(name = "seq_generator", sequenceName = "SEQ_SOCIAL_MEDIA_USERS", allocationSize = 1)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="EMAIL")
	private String email;

	@Column(name="NAME")
	private String name;
	
	@Column(name="MOBILE_NUMBER")
	private String mobile;
	
	@Column(name="UTM_SOURCE")
	private String utmSource;
	
	@Column(name="UTM_MEDIUM")
	private String utmMedium;
	
	@Column(name="UTM_CAMPAIGN")
	private String utmCampaign;
	
	@Column(name="UTM_TERM")
	private String utmTerm;
	
	@Column(name="UTM_CONTENT")
	private String utmContent;
	
	@Column(name="USER_IP")
	private String userIP;
	
	@Column(name="LEAD_VERIFIED")
	private String leadVerified;
	
	@Column(name="CREATED_ON")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	
	@Column(name="TEMP_CUSTOMER_ID")
	private String tempCustomerId;
	
	@Column(name="CID")
	private String cid;
}
