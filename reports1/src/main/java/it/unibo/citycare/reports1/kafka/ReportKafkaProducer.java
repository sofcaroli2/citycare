package it.unibo.citycare.reports1.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReportKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public ReportKafkaProducer(KafkaTemplate<String, String> kafkaTemplate,
            ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendReportCreated(KafkaReportsDTO dto) {
        System.out.println("KAFKA: preparo invio evento -> " + dto);
        try {
            String json = objectMapper.writeValueAsString(dto);
            System.out.println("KAFKA: JSON -> " + json);
            kafkaTemplate.send("reports.created", json);
            System.out.println("KAFKA: messaggio inviato");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}