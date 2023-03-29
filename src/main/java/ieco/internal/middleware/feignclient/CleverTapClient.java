package ieco.internal.middleware.feignclient;

import ieco.internal.middleware.domain.request.ClevertapEventsRequest;
import ieco.internal.middleware.domain.response.ClevertapEventsResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.ws.rs.Path;

@Path("https://in1.api.clevertap.com")
@RegisterRestClient
public interface CleverTapClient {

    @PostMapping("/1/upload")
    ClevertapEventsResponse uploadCleverTapEvents(@RequestBody ClevertapEventsRequest clevertapEventsRequest,
                                                                  @RequestHeader("X-CleverTap-Account-Id") String cleverTapAccountId,
                                                                  @RequestHeader("X-CleverTap-Passcode") String cleverTapPasscode);

}
