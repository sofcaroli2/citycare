package it.unibo.citycare.solutions2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.unibo.citycare.solutions2.entity.Solutions;

import java.util.UUID;
import java.util.List;

public interface SolutionsRepository extends JpaRepository<Solutions, UUID> {

    List<Solutions> findByUserIdAndState(UUID userId, Boolean state); 
    
    List<Solutions> findByState(Boolean state);

}