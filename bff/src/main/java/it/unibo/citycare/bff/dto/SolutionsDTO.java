package it.unibo.citycare.bff.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record SolutionsDTO(
        UUID id,
        LocalDateTime timeSolution,
        String descriptionSolution,
        String title,
        String descriptionReport,
        String address,
        UUID userID,
        Boolean state,
        String residence) {

}
