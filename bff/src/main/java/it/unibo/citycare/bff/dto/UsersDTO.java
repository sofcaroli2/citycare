package it.unibo.citycare.bff.dto;

import java.util.UUID;

public record UsersDTO(
        UUID userId,
        String username,
        String name,
        String surname,
        String residence,
        Boolean isAdmin) {
}