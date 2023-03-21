package ieco.internal.middleware.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"allowedValues",
"defaultValue",
"sortBy",
"isMandatory"
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ZohoUpdateLayoutFieldsRequest {

@JsonProperty("allowedValues")
private List<String> allowedValues;
@JsonProperty("defaultValue")
private String defaultValue;
@JsonProperty("sortBy")
private String sortBy;
@JsonProperty("isMandatory")
private String isMandatory;
/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "ZohoUpdateLayoutFieldsRequest [allowedValues=" + allowedValues + ", defaultValue=" + defaultValue
			+ ", sortBy=" + sortBy + ", isMandatory=" + isMandatory + "]";
}




}