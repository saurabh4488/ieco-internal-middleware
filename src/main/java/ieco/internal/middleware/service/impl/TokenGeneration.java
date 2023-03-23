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

}
