package ieco.internal.middleware.domain.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CallbackRequest {

    @JsonAlias("iecoId")
    @NotBlank(message = "IECO ID is mandatory")
    private String customerId;

    @NotBlank(message = "subject is mandatory")
    private String subject;

    private String desc;

}
