package it.unibo.citycare.bff.dto;

import java.time.LocalTime;
import java.util.UUID;

public record ReportsDTO(
    UUID id, 
    String title, 
    String descriptionReport, 
    String address, 
    UUID userID, 
    LocalTime timeReport, 
    Boolean state) {
    
}
