package ieco.internal.middleware.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="WHATSAPP_TICKET_DETAILS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZohoTicketsFromWhatsApp {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator")
	@SequenceGenerator(name = "seq_generator", sequenceName = "SEQ_WHATSAPP_TICKETS_DETAILS", allocationSize = 1)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "CUSTOMER_ID")
	private String customerId;
	
	@Column(name = "TICKET_ID")
	private Long ticketId;
	
	@Column(name = "CREATED_ON")
	private Date createdOn;
	
	@Column(name = "UPDATED_ON")
	private Date updatedOn;
	
	@Column(name = "TICKET_STATUS")
	private String ticketStatus;
	
}
