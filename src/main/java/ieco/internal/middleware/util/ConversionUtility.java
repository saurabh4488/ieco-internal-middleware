package ieco.internal.middleware.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class ConversionUtility {

	
	public static String pojo2jsonString(Object object) throws JsonProcessingException {
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		
		return ow.writeValueAsString(object);
	}
}
