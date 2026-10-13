package it.unibo.citycare.bff.client;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import it.unibo.citycare.bff.dto.SolutionUpdateDTO;
import it.unibo.citycare.bff.dto.SolutionsDTO;

@Component
public class SolutionsClient {

    private final RestTemplate restTemplate;
    private final String localUrl;

    public SolutionsClient(final RestTemplate restTemplate,
            final @Value("${api.solutions2-service.localurl}") String localUrl) {
        this.restTemplate = restTemplate;
        this.localUrl = localUrl;
    }

    public SolutionsDTO[] getSolutionsByUserIdAndState(UUID userId, Boolean state) {
        String url = localUrl + "/solutions/user/" + userId + "/state/" + state;
        return restTemplate.getForObject(url, SolutionsDTO[].class);
    }

    public SolutionsDTO[] getSolutionsByState(Boolean state) {
        String url = localUrl + "/solutions/state/" + state;
        return restTemplate.getForObject(url, SolutionsDTO[].class);
    }

    public void updateSolution(SolutionUpdateDTO solution) {
        String url = localUrl + "/solutions";

        HttpEntity<SolutionUpdateDTO> request = new HttpEntity<>(solution);

        restTemplate.exchange(
                url,
                HttpMethod.PUT,
                request,
                Void.class);
    }
}