
package ieco.internal.middleware.domain.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Result"
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class KsecTicketCreationResponse {

    @JsonProperty("Result")
    private Result result;
    @JsonProperty("StatusCode")
    private int statusCode;
    @JsonProperty("Message")
    private String message;
    
    private String interactionId;
	
	
   
   
}
