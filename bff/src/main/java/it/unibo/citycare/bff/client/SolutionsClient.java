package it.unibo.citycare.bff.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class SolutionsClient {

    private final RestTemplate restTemplate;
    private final String localUrl;

    public SolutionsClient(final RestTemplate restTemplate,final @Value("${api.solutions2-service.localurl}") String localUrl) {
        this.restTemplate = restTemplate;
        this.localUrl = localUrl;
    }
}
