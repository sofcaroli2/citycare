package it.unibo.citycare.bff.dto;

import java.util.UUID;

// selezione di dati necessari per aggiornare la soluzione
public record SolutionUpdateDTO(
    UUID id,
    String descriptionSolution,
    Boolean state
) {
}
