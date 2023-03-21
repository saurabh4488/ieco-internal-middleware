package ieco.internal.middleware.domain.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
@Data
public class ClevertapEventsResponse {
    private String status;
    private long processed;
    @JsonProperty("unprocessed")
    private List<Unprocessed> unprocessed;

    public static class Unprocessed{
        private String status;
        private String code;
        private String error;
        private String record;
    }
}
