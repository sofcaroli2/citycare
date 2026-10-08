package it.unibo.citycare.bff.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import it.unibo.citycare.bff.client.UsersClient;
import it.unibo.citycare.bff.dto.UsersDTO;

@Service
public class UsersService {

    private final UsersClient usersClient;

    public UsersService(UsersClient usersClient) {
        this.usersClient = usersClient;
    }

    public UsersDTO getUserById(UUID userId) {
        return usersClient.getUserById(userId);
    }
}