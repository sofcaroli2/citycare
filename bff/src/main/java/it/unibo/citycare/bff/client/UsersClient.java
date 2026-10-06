package it.unibo.citycare.bff.client;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import it.unibo.citycare.bff.dto.UsersDTO;

@Component
public class UsersClient {

    private final RestTemplate restTemplate;
    private final String localUrl;

    public UsersClient(RestTemplate restTemplate,@Value("${api.reports1-service.localurl}") String localUrl) {
        this.restTemplate = restTemplate;
        this.localUrl = localUrl;
    }

    public UsersDTO getUserById(UUID userId) {
        String url = localUrl + "/user/" + userId;
        return restTemplate.getForObject(url, UsersDTO.class);
    }
}