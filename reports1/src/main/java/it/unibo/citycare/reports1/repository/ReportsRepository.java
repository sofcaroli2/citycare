package it.unibo.citycare.reports1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.unibo.citycare.reports1.entity.Reports;

import java.util.UUID;
import java.util.List;

public interface ReportsRepository extends JpaRepository<Reports, UUID> {
    // findAll() per ottenere tutti i report
    // findById(id) per cercarne uno tramite ID
    // save(report) per inserirlo o aggiornarlo
    // deleteById(id) e delete(report) per eliminarlo
    // count() per contarli
    // existsById(id) per verificare se un ID esiste

    // per ottenere tutti i report di un utente specifico con state = 0 (non risolti dall'admin) o 1 (risolti dall'admin)
    List<Reports> findByUserIdAndState(UUID userId, Boolean state); 
    
    // per ottenere tutti i report con state = 0 (non risolti dall'admin) o 1 (risolti dall'admin)
    List<Reports> findByState(Boolean state);
}