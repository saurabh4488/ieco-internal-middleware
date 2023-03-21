package ieco.internal.middleware.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"debounce",
"success",
"balance"
})
@Getter
@Setter
public class DebounceResponse {

@JsonProperty("debounce")
private Debounce debounce;
@JsonProperty("success")
private String success;
@JsonProperty("balance")
private String balance;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@Getter
@Setter
public static class Debounce {

@JsonProperty("email")
public String email;
@JsonProperty("code")
public String code;
@JsonProperty("role")
public String role;
@JsonProperty("free_email")
public String freeEmail;
@JsonProperty("result")
public String result;
@JsonProperty("reason")
public String reason;
@JsonProperty("send_transactional")
public String sendTransactional;
@JsonProperty("did_you_mean")
public String didYouMean;
@JsonProperty("error")
public String error;
@JsonIgnore
public Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
}