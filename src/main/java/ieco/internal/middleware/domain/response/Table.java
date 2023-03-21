
package ieco.internal.middleware.domain.response;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Table {

    @JsonProperty("ERROR_MESSAGE")
    @JsonAlias("error_message")
    private String eRRORMESSAGE;
    
    @JsonProperty("Query_Id")
    @JsonAlias("query_id")
    private BigDecimal queryId;
   
    @JsonProperty("Interaction_id")
    @JsonAlias("interaction_id")
    private String interactionId;
    
    @JsonProperty("SRNATURE_Id")
    private String SRNATURE_Id;
    
    @JsonProperty("SRNATURE_name")
    private String SRNATURE_name;
    
    @JsonProperty("Product_ID")
    private String Product_ID;
    
        
    @JsonProperty("Product_Cd")
    private String Product_Cd;


   @JsonProperty("CallType_Id")
    @JsonAlias("CallType_ID")
    private String CallType_Id;
    
    @JsonProperty("CallTypeName")
    private String CallTypeName;
    
    @JsonProperty("SubCallType_ID")
    private String SubCallType_ID;
    
    @JsonProperty("SubCallTypeName")
    private String SubCallTypeName;
    
    @JsonProperty("Source_ID")
    private String Source_ID;
    
    @JsonProperty("Source_Desc")
    private String Source_Desc;
    
    @JsonProperty("Severity_Id")
    private String Severity_Id;
    
    @JsonProperty("Severity_Name")
    private String Severity_Name;
    
    @JsonProperty("Status_ID")
    private String Status_ID;
    
    @JsonProperty("Status_Desc")
    private String Status_Desc;
    
    @JsonProperty("Department_id")
    private String Department_id;
    
    @JsonProperty("Department_name")
    private String Department_name;
    
 
   
	
	
}
