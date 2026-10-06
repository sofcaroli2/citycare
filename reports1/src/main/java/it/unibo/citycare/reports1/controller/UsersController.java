package it.unibo.citycare.reports1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.PathVariable;

import it.unibo.citycare.reports1.service.UsersService;
import it.unibo.citycare.reports1.entity.Users;

@RestController
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public Users getUserById(@PathVariable UUID userId) {
        return usersService.getUserById(userId);
    }
}
