package it.unibo.citycare.bff.dto;

import java.time.LocalTime;
import java.util.UUID;

// selezione di dati necessari al frontend
public record ReportsRequestDTO(
    UUID id,
    String title, 
    String descriptionReport, 
    String address, 
    LocalTime timeReport) {
    
}