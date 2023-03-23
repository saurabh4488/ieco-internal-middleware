package ieco.internal.middleware.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
@Data
public class ClevertapEventsRequest implements Serializable {


    @JsonProperty("d")
    private transient List<D> d;
    @NoArgsConstructor
    @ToString
    @Builder
    @AllArgsConstructor
    @Data
    public static class D {
        @JsonProperty("identity")
        private String identity;
        @JsonProperty("ts")
        private long ts;
        @JsonProperty("type")
        private String type;
        @JsonProperty("evtName")
        private String evtName;
        @JsonProperty("evtData")
        private EvtData evtData;

        @NoArgsConstructor
        @ToString
        @Builder
        @AllArgsConstructor
        @Data
        public static class EvtData {
            @JsonProperty("Product Category")
            private String productCategory;
            @JsonProperty("disposition")
            private String disposition;
            @JsonProperty("channel")
            private String channel;
        }
    }
}
