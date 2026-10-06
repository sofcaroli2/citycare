package it.unibo.citycare.reports1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

import it.unibo.citycare.reports1.entity.Users;
import it.unibo.citycare.reports1.repository.UsersRepository;

@Service 
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Users getUserById(UUID userId) {
        return usersRepository.findById(userId).orElse(null);
    }
}
