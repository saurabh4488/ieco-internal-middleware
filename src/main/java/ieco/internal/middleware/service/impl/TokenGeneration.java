package ieco.internal.middleware.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class TokenGeneration {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${userId}")
    private String userName;

    @Value("${password}")
    private String password;

    @Value("${tokenGen}")
    private String tokenGenURL;
/*
    public String generateToken() throws JsonProcessingException {
        //System.out.println("tokenGenURL" + tokenGenURL);
        //System.out.println("userName :" + userName);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "client_credentials");
        //System.out.println(new HttpEntity(form, createHeaders(userName, password)));
        ResponseEntity<String> tokenResponse = restTemplate.postForEntity(tokenGenURL,
                new HttpEntity(form, createHeaders(userName, password)), String.class);
        //System.out.println(tokenResponse.getBody());
        JsonNode actualObj = new ObjectMapper().readTree(tokenResponse.getBody());
        tokenResponse = null;
        Runtime.getRuntime().gc();
        return actualObj.get("access_token").asText();
    }

    HttpHeaders createHeaders(String username, String password) {
        HttpHeaders headers = new HttpHeaders();
        final byte[] encodedAuth = Base64.getEncoder().encode((username + ":" + password).getBytes(Charset.forName("US-ASCII")));
        String str = new String(encodedAuth).intern();
        String authHeader = "Basic " + str;
        str = null;
        headers.set("Authorization", authHeader);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return headers;
    }*/
}
