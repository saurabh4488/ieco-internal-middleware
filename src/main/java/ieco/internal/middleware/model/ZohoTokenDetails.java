package ieco.internal.middleware.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="ZOHO_TOKEN_DETAILS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZohoTokenDetails {
	
	@Id
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator")
	@SequenceGenerator(name = "seq_generator", sequenceName = "SEQ_ZOHO_TOKEN_DETAILS", allocationSize = 1)
	private int id;
	
	@Column(name = "ZOHO_TOKEN")
	private String token;
	
	@Column(name = "CREATED_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;
	
	@Column(name = "EXPIRES_IN")
	private Integer expiresIn;

}
