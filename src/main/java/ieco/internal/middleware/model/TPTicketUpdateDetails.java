package ieco.internal.middleware.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="TP_TICKET_DETAILS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TPTicketUpdateDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator")
	@SequenceGenerator(name = "seq_generator", sequenceName = "SEQ_TP_DETAILS", allocationSize = 1)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "FIRST_CALL_DATE")
	private String firstCallDate;
	
	@Column(name = "CONNECTED_DATE")
	private String connectedDate;
	
	@Column(name = "DISPOSITION")
	private String disposition;
	
	@Column(name = "ATTEMPT")
	private Integer attempt;
	
	@Column(name = "PLATFORM_TYPE")
	private String platformType;
	
	@Column(name = "REMARKS")
	private String remarks;
	
	@Column(name = "CUSTOMER_ID")
	private Integer iecoId;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "TICKET_ID")
	private long ticketId;
	
	@Column(name="IS_PUSH_TO_ZOHO")
	private String isPushedToZoho;

}
