package ieco.internal.middleware.feignclient;

import ieco.internal.middleware.domain.request.ClevertapEventsRequest;
import ieco.internal.middleware.domain.response.ClevertapEventsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="cleverTap-client",url="${cleverTap.event.url}")
public interface CleverTapClient {

    @PostMapping("/1/upload")
    ClevertapEventsResponse uploadCleverTapEvents(@RequestBody ClevertapEventsRequest clevertapEventsRequest,
                                                                  @RequestHeader("X-CleverTap-Account-Id") String cleverTapAccountId,
                                                                  @RequestHeader("X-CleverTap-Passcode") String cleverTapPasscode);

}
