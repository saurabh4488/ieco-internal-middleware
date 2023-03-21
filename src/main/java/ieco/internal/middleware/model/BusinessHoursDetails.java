package ieco.internal.middleware.model;/*
 * package ieco.internal.middleware.model;
 * 
 * import java.io.Serializable;
 * 
 * import javax.persistence.Column; import javax.persistence.Entity; import
 * javax.persistence.GeneratedValue; import javax.persistence.GenerationType; import
 * javax.persistence.Id; import javax.persistence.SequenceGenerator; import javax.persistence.Table;
 * 
 * import com.ieco.commons.model.BaseEntityAuditWithOutId;
 * 
 * import lombok.AllArgsConstructor; import lombok.Builder; import lombok.Getter; import
 * lombok.NoArgsConstructor; import lombok.Setter; import lombok.ToString;
 * 
 * @Entity
 * 
 * @Table(name = "CHERRY_BUSINESS_HOURS_DETAILS")
 * 
 * @Getter
 * 
 * @Setter
 * 
 * @Builder
 * 
 * @ToString
 * 
 * @NoArgsConstructor
 * 
 * @AllArgsConstructor public class BusinessHoursDetails extends BaseEntityAuditWithOutId implements
 * Serializable {
 * 
 * private static final long serialVersionUID = 1L;
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator")
 * 
 * @SequenceGenerator(name = "seq_generator", sequenceName = "SEQ_CHERRY_BUSINESS_HOURS_DETAILS",
 * allocationSize = 1)
 * 
 * @Column(name = "BUSINESS_HOURS_DETAILS_ID") private Integer id;
 * 
 * @Column(name = "DAY") private String day;
 * 
 * @Column(name = "START_TIME") private String startTime;
 * 
 * @Column(name = "END_TIME") private String endTime;
 * 
 * @Column(name = "IS_ACTIVE") private String isActive;
 * 
 * }
 */
