package it.unibo.citycare.bff.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record SolutionsDTO(
    UUID id, 
    String title, 
    String descriptionSolution, 
    String address, 
    UUID userID, 
    LocalDateTime timeSolution, 
    Boolean state) {
    
}
