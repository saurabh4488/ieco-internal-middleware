package ieco.internal.middleware.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="WAITINGLIST_ADMIN_DETAILS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminDetails {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name = "EMAIL_ID")
	private String emailId;
	
	@Column(name = "MOBILE_NUMBER")
	private String mobileNumber;
	
	@Column(name = "FULL_NAME")
	private String fullName;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name = "CREATED_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;
	
	@Column(name = "UPDATED_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTime;
	@Column(name = "TOKEN")
	private String token;
}
