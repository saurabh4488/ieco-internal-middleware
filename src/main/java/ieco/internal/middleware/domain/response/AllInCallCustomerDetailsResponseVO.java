package ieco.internal.middleware.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

// TODO: Auto-generated Javadoc

/**
 * Gets the pan number.
 *
 * @return the pan number
 */
@Getter

/**
 * Sets the pan number.
 *
 * @param panNumber the new pan number
 */
@Setter

/**
 * Instantiates a new all in call customer details response VO.
 *
 * @param firstName the first name
 * @param lastName the last name
 * @param mobile the mobile
 * @param email the email
 * @param customerId the customer id
 * @param dob the dob
 * @param bankCrn the bank crn
 * @param ksecClientCode the ksec client code
 * @param panNumber the pan number
 */
@AllArgsConstructor

/**
 * Instantiates a new all in call customer details response VO.
 */
@NoArgsConstructor
@ApiModel(description = "Response for Customer Details")

/**
 * To string.
 *
 * @return the java.lang. string
 */
@ToString

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Builder
public class AllInCallCustomerDetailsResponseVO {

/** User name. */
	
	@ApiModelProperty
	@JsonProperty("fname")
	private String firstName;
	
	/** The last name. */
	@ApiModelProperty
	@JsonProperty("lname")
	private String lastName;
	
	/** User mobile. */
	@ApiModelProperty
	@JsonProperty("mobile")
	private String mobile;
	
	/** User email. */
	@ApiModelProperty
	@JsonProperty("email")
	private String email;
	
	/** The customer id. */
	@ApiModelProperty
    @JsonProperty("customerId")

    Integer customerId;
	
	/** The dob. */
	@ApiModelProperty
    @JsonProperty("dob")
    Date dob;

	/** Bank CRN. */
	@ApiModelProperty
    @JsonProperty("bankCrn")
	private String bankCrn;

	/** Bank CRN. */
	@ApiModelProperty
    @JsonProperty("ksecClientCode")
	private String ksecClientCode;

	/** The pan number. */
	@ApiModelProperty
	@JsonProperty("panNumber")
	private String panNumber;
}
