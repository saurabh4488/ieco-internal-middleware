package ieco.internal.middleware.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeskContactCreationRequest {

	private DeskContactCreationCustomFields cf;

	private String zip;

	private String lastName;
	
	private String firstName;

	private String country;

	private String secondaryEmail;

	private String city;

	private String facebook;

	private String mobile;

	private String description;

	private String ownerId;

	private String type;

	private String title;

	private String accountId;

	private String phone;

	private String street;

	private String state;

	private String email;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DeskContactCreationRequest [cf=" + cf + ", zip=" + zip + ", lastName=" + lastName + ", country="
				+ country + ", secondaryEmail=" + secondaryEmail + ", city=" + city + ", facebook=" + facebook
				+ ", mobile=" + mobile + ", description=" + description + ", ownerId=" + ownerId + ", type=" + type
				+ ", title=" + title + ", accountId=" + accountId + ", phone=" + phone + ", street=" + street
				+ ", state=" + state + ", email=" + email + "]";
	}

}
