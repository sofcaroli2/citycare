package it.unibo.citycare.bff.dto;

// selezione di dati necessari al frontend
public record UsersRequestDTO(
    String username,
    String name,
    String surname,
    String residence) {
}