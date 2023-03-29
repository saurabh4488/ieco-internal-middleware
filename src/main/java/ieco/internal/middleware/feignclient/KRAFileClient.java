package ieco.internal.middleware.feignclient;

import ieco.internal.middleware.domain.request.KraProcessDocRequestVO;
import ieco.internal.middleware.domain.response.ResponseObject;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.Path;

/**
 * The interface KRA file client to extract CVL KRA file zip.
 */

@Path("http://10.51.164.10:3000/")
@RegisterRestClient
public interface KRAFileClient {

	
	@ResponseBody
    @PostMapping( value ="doc/process", consumes = {"multipart/form-data"})
    ResponseObject processCvlKraDocumentsZip(KraProcessDocRequestVO kraProcessDocRequestBody);


	
}
