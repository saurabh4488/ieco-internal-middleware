package ieco.internal.middleware.feignclient;

import ieco.internal.middleware.domain.request.PanImageRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PanOcrRetrieveImgClient", url = "${panOcrRetrieveImgClientPath}")
public interface PanOcrRetrieveImgClient {
      @PostMapping(value ="/", produces = MediaType.IMAGE_PNG_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
      byte[] retrieveImage(@RequestBody PanImageRequest panImageRequest);
}