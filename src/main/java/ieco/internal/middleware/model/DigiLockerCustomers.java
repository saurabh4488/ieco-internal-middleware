package ieco.internal.middleware.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="DIGILOCKER_CUSTOMERS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DigiLockerCustomers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator")
	@SequenceGenerator(name = "seq_generator", sequenceName = "SEQ_DG_USER_DETAILS", allocationSize = 1)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "CUSTOMER_ID")
	private String customerId;
	
	@Column(name = "DG_ACCESS_TOKEN")
	private String access_token;
	
	@Column(name = "DG_REFRESH_TOKEN")
	private String refresh_token;
	
	@Column(name = "DG_EAADHAAR")
	private String eaadhaar;
	
	@Column(name = "DG_NAME")
	private String name;
	
	@Column(name = "DOB")
	private String dob;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "CREATED_ON")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	
	@Column(name = "MODIFIED_ON")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedOn;
	
	@Column(name = "IS_AADHAAR_DATA_RECEIVED")
	private String isAadhaarDataReceived;
	
	@Column(name = "IS_AADHAAR_PDF_RECEIVED")
	private String isAadhaarPDFReceived;
	
	@Column(name = "IS_PAN_DATA_RECEIVED")
	private String isPANDataReceived;
	
	@Column(name = "IS_PAN_IMAGE_RECEIVED")
	private String isPANImageReceived;
	
	@Column(name = "FINAL_RES")
	private String finalRes;
	
	@Column(name = "DIGI_LOCKER_ID")
	private String digilockerid;
	
	@Column(name="AADHAAR_XML")
	private String aadhaarXML;
	
	@Column(name="AADHAAR_DOC")
	private String aadhaarDoc;
	
	@Column(name="IS_CELUSION_FLOW")
	private String isCelusion;
}
