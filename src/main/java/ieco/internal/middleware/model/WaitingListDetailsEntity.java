package ieco.internal.middleware.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "WAITINGLIST_USER_DETAILS")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WaitingListDetailsEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator")
	@SequenceGenerator(name = "seq_generator", sequenceName = "SEQ_WAITINGLIST_USER_DETAILS", allocationSize = 1)
	@Column(name = "ID")
	private Integer id;

	/*
	 * @Column(name="WAITING_LIST_NUMBER",nullable=false,columnDefinition =
	 * "BIGINT DEFAULT nextval('SEQ_WAITINGLIST_NUMBER')")
	 * 
	 * @Generated(GenerationTime.INSERT)
	 */
	@Column(name = "WAITING_LIST_NUMBER")
	private Integer waitingListNumber;

	@Column(name = "EMAIL_ID")
	private String emailId;

	@Column(name = "MOBILE_NUMBER")
	private String mobileNumber;

	@Column(name = "FULL_NAME")
	private String fullName;

	@Column(name = "REF_CODE")
	private String referenceCode;

	/*
	 * @Column(name = "WAITING_LIST_NUMBER") private String waitingNumber;
	 */

	@Column(name = "LINKEDIN_URL")
	private String linkedinURL;

	@Column(name = "ISACCESS_PROVIDED")
	private String isAccessProvided;

	@Column(name = "REFERED_BY_CODE")
	private String referedByCode;

	@Column(name = "REFERED_BY_EMAIL_ID")
	private String referedByEmail;

	@Column(name = "CHECK1")
	private String check1;

	@Column(name = "CHECK2")
	private String check2;

	@Column(name = "CREATED_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;

	@Column(name = "UPDATED_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTime;

	@Column(name = "ACCESS_ALLOWED_ON")
	@Temporal(TemporalType.TIMESTAMP)
	private Date accessAllowedOn;

	@Column(name = "ACCESS_ALLOWED_BY")
	private String accessAllowedBy;

	@Column(name = "OFFSET")
	private Integer offset;

	@Column(name = "IS_FIRST_TIME")
	private String isFirstTime;

	@Column(name = "REFERED_COUNT")
	private Integer referedCount;
	
	@Column(name = "SOURCE")
	private String source;
	
	@Column(name = "MEDIUM")
	private String medium;
	
	@Column(name = "EXTRAS")
	private String extras;
	
	@Column(name = "INVITATION_URL")
	private String invitationURL;
	
	/*@Column(name= "IS_EMAIL_VALID")
	private String isEmailValid;*/
}
