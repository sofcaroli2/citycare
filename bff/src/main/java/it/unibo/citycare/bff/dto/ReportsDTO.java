package it.unibo.citycare.bff.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReportsDTO(
    UUID id, 
    String title, 
    String descriptionReport, 
    String address, 
    UUID userID, 
    LocalDateTime timeReport, 
    Boolean state,
    String residence) {
    
}
