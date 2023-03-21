package ieco.internal.middleware.feignclient;

import ieco.internal.middleware.domain.response.DigiLockerAccessTokenRes;
import ieco.internal.middleware.domain.response.DigiLockerIssuedDocumentsRes;
import ieco.internal.middleware.domain.response.DigiLockerPanRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "digi-locker", url = "${digilocker.baseurl}")
public interface DigiLockerClient {

    @PostMapping("/1/token")
    ResponseEntity<DigiLockerAccessTokenRes> fetchAccessToken(MultiValueMap<String, Object> requestMap);

    @GetMapping("/1/file/{uri}")
    byte[] getFile(@PathVariable("uri") String uri, @RequestHeader("Authorization") String token);

    @GetMapping("/2/files/issued")
    DigiLockerIssuedDocumentsRes getIssuedDocuments(@RequestHeader("Authorization") String token);

    @GetMapping("/3/xml/eaadhaar")
    ResponseEntity<String> getAadhaarXML(@RequestHeader("Authorization") String token);

    @GetMapping("/1/xml/{uri}")
    DigiLockerPanRes getPanData(@PathVariable("uri") String uri, @RequestHeader("Authorization") String token);

}
