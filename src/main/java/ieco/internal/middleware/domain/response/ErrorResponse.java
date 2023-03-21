package ieco.internal.middleware.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ErrorResponse 
{
    
    //General error message about nature of error
    private String message;
 
    //Specific errors in API request processing
    private List<Object> details;
 
    //Getter and setters
}