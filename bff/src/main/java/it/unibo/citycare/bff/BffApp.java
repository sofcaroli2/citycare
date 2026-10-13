package it.unibo.citycare.bff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import it.unibo.citycare.bff.security.TokenInterceptor;

@SpringBootApplication
public class BffApp {

    public static void main(final String[] args) {
        SpringApplication.run(BffApp.class, args);
    }

    @Bean
    public RestTemplate restTemplate(
            TokenInterceptor tokenInterceptor) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(tokenInterceptor);
        return restTemplate;
    }

}