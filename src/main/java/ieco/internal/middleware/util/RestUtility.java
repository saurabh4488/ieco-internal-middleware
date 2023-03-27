package ieco.internal.middleware.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Iterator;
import java.util.Map;

@Slf4j
@Component
public class RestUtility {

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * HTTP POST Call
	 * @param <T>
	 * @param <P>
	 * @param url
	 * @param mapheaders
	 * @param request
	 * @param response
	 * @return
	 */
	public <T, P> ResponseEntity<T> post(String url, Map<String, String> mapheaders, P request, Class<T> response) {

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

		log.info(builder.build().toUri());
		return restTemplate.postForEntity(builder.build().toUri(), new HttpEntity<>(request, createHeaders(mapheaders)),
				response);
	}

	/**
	 * HTTP GET Call
	 * @param <T>
	 * @param url
	 * @param mapheaders
	 * @param response
	 * @return
	 */
	public <T> ResponseEntity<T> get(String url, Map<String, String> mapheaders, Class<T> response) {

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

		return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(createHeaders(mapheaders)),
				response);
	}

	/**
	 * @param mapheaders
	 * @return HttpHeaders
	 */
	public HttpHeaders createHeaders(Map<String, String> mapheaders) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		if (!isCollectionMapNullOrEmpty(mapheaders)) {
			Iterator<String> iterator = mapheaders.keySet().iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();

				headers.add(key, mapheaders.get(key));
			}


			return headers;
		} else {
			return headers;
		}

	}

	public <T> boolean checkStatus(ResponseEntity<T> response) {

		
		return response.getStatusCodeValue() == 200 ? true : false;

	}

	/**
	 * @param m
	 * @return 
	 */
	protected static boolean isCollectionMapNullOrEmpty(final Map<?, ?> m) {
		return m == null || m.isEmpty();
	}
	
	
}
