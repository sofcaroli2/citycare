package it.unibo.citycare.solutions2.kafka;

import java.util.UUID;

// dati inviati tramite kafka
public record KafkaReportsDTO(
    UUID id,
    String title, 
    String descriptionReport, 
    String address, 
    String residence,
    UUID userId) {
}