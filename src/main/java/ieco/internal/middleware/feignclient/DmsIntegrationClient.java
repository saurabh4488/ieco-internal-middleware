package ieco.internal.middleware.feignclient;

import ieco.internal.middleware.domain.response.ResponseObject;
import ieco.internal.middleware.enums.DmsDocumentTypeEnum;
import feign.Logger;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * The interface Dms integration client.
 *
 * @author AlokeT
 */
@FeignClient(name = "ieco-integration/v1/integration/dms", configuration = {DmsIntegrationClient.MultipartSupportConfig.class})
public interface DmsIntegrationClient {



	/**
	 * Download documents as byte array byte [ ].
	 *
	 * @param documentId the document id
	 * @return the byte [ ]
	 */
	@GetMapping(value = "/downloadDoc/byte/{documentId}")
	public byte[] downloadDocumentsAsByteArray(@PathVariable(value = "documentId") String documentId);

	/**
	 * Download documents as base 64 string.
	 *
	 * @param sessionId  the session id
	 * @param documentId the document id
	 * @return the string
	 */
	@GetMapping(value = "/downloadDoc/base64/{documentId}")
	public String downloadDocumentsAsBase64(@RequestHeader(value = "sessionId", required = false) String sessionId, @PathVariable(value = "documentId") String documentId);

	/**
	 * Update dms document integer.
	 *
	 * @param customerId    the customer id
	 * @param documentId    the document id
	 * @param multipartFile the multipart file
	 * @return the integer
	 */
	@PostMapping(value = "/updateDoc/{documentId}", consumes = {"multipart/form-data"})
	public Integer updateDmsDocument(@RequestHeader(value = "customerId", required = false) Integer customerId, @PathVariable(value = "documentId") String documentId, @RequestBody MultipartFile multipartFile);

	/**
	 * Delete existing doc by id integer.
	 *
	 * @param documentId the document id
	 * @return the integer
	 */
	@DeleteMapping(value = "/deleteDoc/{documentId}")
	public Integer deleteExistingDocById(@PathVariable(value = "documentId") String documentId);

	@PostMapping(value = "/uploadDoc/{customerId}/{dmsDocumentType}", consumes = "multipart/form-data")
	ResponseEntity<ResponseObject> uploadDocuments(@RequestHeader(value = "sessionId", required = false) String sessionId,
												   @RequestPart(value = "file") MultipartFile file,
												   @PathVariable(value = "dmsDocumentType") DmsDocumentTypeEnum dmsDocumentTypeEnum,
												   @PathVariable(value = "customerId") Integer customerId);


	/**
	 * The type Multipart support config.
	 */
	//@Configuration
	class MultipartSupportConfig {
		/**
		 * Feign form encoder encoder.
		 *
		 * @return the encoder
		 */
	    @Bean
	    @Primary
	    @Scope("prototype")
	    public Encoder feignFormEncoder() {
	        return new SpringFormEncoder();
	    }
	    

		/**
		 * Feign logger level logger . level.
		 *
		 * @return the logger . level
		 */
		@Bean
		Logger.Level feignLoggerLevel() {
			return Logger.Level.HEADERS;
		}
	}
}
