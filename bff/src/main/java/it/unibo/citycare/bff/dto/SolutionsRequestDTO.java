package it.unibo.citycare.bff.dto;

import java.time.LocalDateTime;

// selezione di dati necessari al frontend
public record SolutionsRequestDTO(
        String title,
        String descriptionSolution,
        String address,
        LocalDateTime timeReport,
        String residence) {
}