package it.unibo.citycare.bff.dto;

import java.time.LocalDateTime;

// selezione di dati necessari al frontend
public record ReportsRequestDTO(
    String title, 
    String descriptionReport, 
    String address, 
    LocalDateTime timeReport,
    String residence) {
}