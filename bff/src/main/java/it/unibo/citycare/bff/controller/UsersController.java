package it.unibo.citycare.bff.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unibo.citycare.bff.dto.UsersRequestDTO;
import it.unibo.citycare.bff.service.UsersService;

@RestController
@RequestMapping("/api/citycare/user")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/{userId}")
    public UsersRequestDTO getUserById(@PathVariable UUID userId) {
        return usersService.getUserById(userId);
    }
}