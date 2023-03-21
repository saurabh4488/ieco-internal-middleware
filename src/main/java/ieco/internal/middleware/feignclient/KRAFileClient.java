package ieco.internal.middleware.feignclient;

import ieco.internal.middleware.domain.request.KraProcessDocRequestVO;
import ieco.internal.middleware.domain.response.ResponseObject;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The interface KRA file client to extract CVL KRA file zip.
 */

@FeignClient(name = "KRAFileClient", url="${kraProcessClient}", configuration = { KRAFileClient.MultipartSupportConfig.class })
public interface KRAFileClient {

	
	@ResponseBody
    @PostMapping( value ="doc/process", consumes = {"multipart/form-data"})
    ResponseObject processCvlKraDocumentsZip(KraProcessDocRequestVO kraProcessDocRequestBody);


	/**
     * The type Multipart support config.
     */
	class MultipartSupportConfig {
        @Bean
        @Primary
        @Scope("prototype")
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder();
        }
    }
	
}
