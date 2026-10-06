package it.unibo.citycare.bff.dto;

import java.time.LocalDateTime;
import java.util.UUID;

// selezione di dati necessari al frontend
public record SolutionsRequestDTO(
    UUID id,
    String title,
    String descriptionSolution,
    String address,
    LocalDateTime timeSolution
) {
}