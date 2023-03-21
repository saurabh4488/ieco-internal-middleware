package ieco.internal.middleware.model;/*
 * package ieco.internal.middleware.model;
 * 
 * import java.io.Serializable; import java.util.Date;
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
 * @Table(name = "HOLIDAY_DETAILS")
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
 * @AllArgsConstructor public class BusinessHolidayDetails extends BaseEntityAuditWithOutId
 * implements Serializable {
 * 
 * private static final long serialVersionUID = 1L;
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator")
 * 
 * @SequenceGenerator(name = "seq_generator", sequenceName = "SEQ_HOLIDAY_DETAILS", allocationSize =
 * 1)
 * 
 * @Column(name = "HOLIDAY_DETAILS_ID") private Integer id;
 * 
 * @Column(name = "HOLIDAY_DATE") private Date holidayDate;
 * 
 * @Column(name = "HOLIDAY_NAME") private String holidayName;
 * 
 * @Column(name = "DAY") private String day;
 * 
 * @Column(name = "IS_ACTIVE") private String isActive;
 * 
 * }
 */
