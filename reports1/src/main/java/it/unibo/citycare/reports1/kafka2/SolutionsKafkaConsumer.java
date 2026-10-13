package it.unibo.citycare.reports1.kafka2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.citycare.reports1.entity.Reports;
import it.unibo.citycare.reports1.repository.ReportsRepository;

@Component
public class SolutionsKafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(SolutionsKafkaConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ReportsRepository reportsRepository;

    @KafkaListener(
        topics = "${spring.kafka.consumer.second-topic.name}",
        groupId = "${spring.kafka.consumer.second-group-id}"
    )
    public void listen(final String message) {
        try {
            KafkaSolutionsDTO event =
                    objectMapper.readValue(message, KafkaSolutionsDTO.class);

            // cerca il report
            Reports report = reportsRepository.findById(event.id())
                    .orElse(null);

            if (report == null) {
                log.warn(
                    "Report non trovato",
                    event.id()
                );
                return;
            }
            report.setState(true);
            reportsRepository.save(report);
            
            log.info(
                "Report aggiornato con id:",
                event.id()
            );

        } catch (JsonProcessingException e) {
            log.warn("Messaggio Kafka non valido: {}", message, e);
        }
    }
}
