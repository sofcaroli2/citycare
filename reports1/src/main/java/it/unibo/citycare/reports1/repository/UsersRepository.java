package it.unibo.citycare.reports1.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import it.unibo.citycare.reports1.entity.Users;

public interface UsersRepository extends JpaRepository<Users, UUID> {
    
}
