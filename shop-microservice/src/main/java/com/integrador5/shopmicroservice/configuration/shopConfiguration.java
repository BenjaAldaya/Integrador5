package com.integrador5.shopmicroservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Permite consumir otro microservicio
 */
@Configuration
public class shopConfiguration {

    @Bean("ClientRest")
    public RestTemplate registerRestTemplate(){
        return new RestTemplate();
    }
}
