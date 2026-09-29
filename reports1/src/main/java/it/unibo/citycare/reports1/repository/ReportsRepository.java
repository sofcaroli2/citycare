package it.unibo.citycare.reports1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.unibo.citycare.reports1.entity.Reports;

import java.util.UUID;

public interface ReportsRepository extends JpaRepository<Reports, UUID> {

}