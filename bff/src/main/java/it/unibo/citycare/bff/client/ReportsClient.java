package it.unibo.citycare.bff.client;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import it.unibo.citycare.bff.dto.ReportsDTO;

@Component
public class ReportsClient {

    private final RestTemplate restTemplate;
    private final String localUrl;

    public ReportsClient(final RestTemplate restTemplate,final @Value("${api.reports1-service.localurl}") String localUrl) {
        this.restTemplate = restTemplate;
        this.localUrl = localUrl;
    }

    

    public ReportsDTO[] getReportsByUserIdAndState(UUID userId, Boolean state) {
        String url = localUrl + "/reports/user/" + userId + "/state/" + state;
        return restTemplate.getForObject(url, ReportsDTO[].class);
    }


}
