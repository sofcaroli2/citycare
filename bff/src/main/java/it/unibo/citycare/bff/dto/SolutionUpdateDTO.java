package it.unibo.citycare.bff.dto;

import java.time.LocalDateTime;
import java.util.UUID;

// selezione di dati necessari per aggiornare la soluzione
public record SolutionUpdateDTO(
    String title, 
    String descriptionSolution, 
    String address, 
    String residence,
    LocalDateTime timeSolution,
    UUID userId) {
}
