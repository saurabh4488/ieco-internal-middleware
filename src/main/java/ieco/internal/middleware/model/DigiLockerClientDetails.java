package ieco.internal.middleware.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="DIGILOCKER_CLIENTS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DigiLockerClientDetails {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name = "APP_NAME")
	private String appName;
	
	@Column(name = "CLIENT_ID")
	private String clientId;
	
	@Column(name = "IS_ACTIVE")
	private String isActive;
	
	@Column(name = "CREATED_ON")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	
	@Column(name = "DG_CLIENT_ID")
	private String dgClientId;
	
	@Column(name = "DG_CLIENT_SECRET")
	private String dgClientSecret;
	
	
	@Column(name = "REDIRECT_URL")
	private String redirectURL;
	
	@Column(name = "STATE")
	private String state;
	
}
