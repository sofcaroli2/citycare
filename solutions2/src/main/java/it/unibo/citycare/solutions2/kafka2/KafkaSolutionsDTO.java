package it.unibo.citycare.solutions2.kafka2;

import java.util.UUID;

// dati inviati tramite kafka, secondo topic
public record KafkaSolutionsDTO(
        UUID id,
        String descriptioSolution,
        Boolean state) {
}