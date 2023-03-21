package ieco.internal.middleware.domain.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;


@AllArgsConstructor

/**
 * Instantiates a new kra process doc request VO.
 */
@NoArgsConstructor
@ApiModel(description = "Request to process KRA CVL Zip doc")

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Builder

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data
public class KraProcessDocRequestVO implements Serializable {
    
    /** The Constant serialVersionUID. */
    @JsonIgnore
    private static final long serialVersionUID = 1L;
    
    
    /** The file. */
    @JsonProperty(value = "file")
    private MultipartFile file;
    
    /** The iecoid. */
    @JsonProperty(value = "iecoid")
    private String iecoid;
    
}
