package ieco.internal.middleware.exception;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class FeignErrorDecoder implements ErrorDecoder {
	
	private Logger logger = LoggerFactory.getLogger(FeignErrorDecoder.class);

	@Override
	public Exception decode(String methodKey, Response response) {
		switch (response.status()) {
		case 400:
			logger.error("Status code {}, methodKey {} , error{}" , response.status() , methodKey , response.reason());
			return new ResponseStatusException(HttpStatus.valueOf(response.status()),"Bad Request");
		case 404: {
			logger.error("Error took place when using Feign client to send HTTP Request. Status code "
					+ response.status() + ", response.reason() = " + response.reason());
			return new ResponseStatusException(HttpStatus.valueOf(response.status()),"Error");
		}
		default:
			logger.error("default Status code {}, methodKey {} , error{}" , response.status() , methodKey , response.reason());
			return new Exception(response.reason());
		}

	}

}
