package it.unibo.citycare.solutions2.kafka2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SolutionsKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public SolutionsKafkaProducer(KafkaTemplate<String, String> kafkaTemplate,
            ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendSolutionUpdated(KafkaSolutionsDTO dto) {
        System.out.println("KAFKA: preparo invio solution -> " + dto);
        try {
            String json = objectMapper.writeValueAsString(dto);
            System.out.println("KAFKA: JSON -> " + json);
            kafkaTemplate.send("solutions.updated", json);
            System.out.println("KAFKA: messaggio aggiornato");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}