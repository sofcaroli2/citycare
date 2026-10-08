package it.unibo.citycare.bff.dto;

import java.util.UUID;

// selezione di dati necessari per salvare il report
public record ReportsSaveDTO(
    String title, 
    String descriptionReport, 
    String address, 
    String residence,
    UUID userId) {
}
