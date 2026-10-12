package it.unibo.citycare.reports1.kafka;

import java.util.UUID;

// dati inviati tramite kafka
public record KafkaReportsDTO(
    UUID id,
    String title, 
    String descriptionReport, 
    String address, 
    String residence) {
}