package it.unibo.citycare.solutions2.kafka;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.citycare.solutions2.entity.Solutions;
import it.unibo.citycare.solutions2.repository.SolutionsRepository;

@Component
public class ReportKafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SolutionsRepository solutionsRepository;

    @KafkaListener(topics = "${spring.kafka.consumer.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(final String message) {
        try {
            final KafkaReportsDTO event = objectMapper.readValue(message, KafkaReportsDTO.class);
            Solutions solution = new Solutions();
            solution.setId(event.id());
            solution.setTitle(event.title());
            solution.setDescriptionReport(event.descriptionReport());
            solution.setAddress(event.address());
            solution.setUserId(event.userId());
            solution.setState(false);
            solution.setResidence(event.residence());
            solutionsRepository.save(solution);
        } catch (final JsonProcessingException e) {
            log.warn("Messaggio Kafka non valido, scartato: {}", message, e);
        }
    }

}