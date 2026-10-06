package it.unibo.citycare.bff.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import it.unibo.citycare.bff.client.UsersClient;
import it.unibo.citycare.bff.dto.UsersDTO;
import it.unibo.citycare.bff.dto.UsersRequestDTO;

@Service
public class UsersService {

    private final UsersClient usersClient;

    public UsersService(UsersClient usersClient) {
        this.usersClient = usersClient;
    }

    public UsersRequestDTO getUserById(UUID userId) {
        final UsersDTO usersDTO = usersClient.getUserById(userId);
        return new UsersRequestDTO(
                usersDTO.username(),
                usersDTO.name(),
                usersDTO.surname(),
                usersDTO.residence()
        );
    }
}