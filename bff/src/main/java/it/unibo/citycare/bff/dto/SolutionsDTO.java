package it.unibo.citycare.bff.dto;

import java.time.LocalTime;
import java.util.UUID;

public record SolutionsDTO(
    UUID id, 
    String title, 
    String descriptionSolution, 
    String address, 
    UUID userID, 
    LocalTime timeSolution, 
    Boolean state) {
    
}
