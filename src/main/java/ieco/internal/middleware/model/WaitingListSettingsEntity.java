package ieco.internal.middleware.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="WAITINGLIST_SETTINGS_DETAILS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WaitingListSettingsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator")
	@SequenceGenerator(name = "seq_generator", sequenceName = "SEQ_WAITINGLIST_SETTINGS_DETAILS", allocationSize = 1)
	@Column(name="ID")
	private int id;
	
	@Column(name = "CREATED_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;
	
	@Column(name = "UPDATED_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTime;
	
	@Column(name = "OFFSET")
	private Integer offset;
	
	
}
