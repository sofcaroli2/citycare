package it.unibo.citycare.bff.dto;

import java.util.UUID;

public record UsersDTO(
    UUID id,
    String username,
    String name,
    String surname,
    String residence) {
}