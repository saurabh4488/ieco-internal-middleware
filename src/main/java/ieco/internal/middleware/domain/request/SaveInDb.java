package ieco.internal.middleware.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import ieco.internal.middleware.domain.response.DigiLockerAccessTokenRes;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SaveInDb {
    @JsonProperty("iecoID")
    public String iecoID;
    @JsonProperty("tokenRes")
    public DigiLockerAccessTokenRes tokenRes;
    @JsonProperty("isAadhaarDataReceived")
    public String isAadhaarDataReceived;
    @JsonProperty("isAadhaarPdfReceived")
    public String isAadhaarPdfReceived;
    @JsonProperty("isPANDataReceived")
    public String isPANDataReceived;
    @JsonProperty("isPANimageReceived")
    public String isPANimageReceived;
    @JsonProperty("finalJSON")
    public String finalJSON;
    @JsonProperty("aadhaarXML")
    public String aadhaarXML;
    @JsonProperty("aadhaarDoc")
    public String aadhaarDoc;
    @JsonProperty("isCelusion")
    public boolean isCelusion;
}
