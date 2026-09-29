package it.unibo.citycare.reports1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import it.unibo.citycare.reports1.entity.Reports;
import it.unibo.citycare.reports1.repository.ReportsRepository;

@Service
public class ReportsService {

    @Autowired
    private ReportsRepository reportsRepository;

    public List<Reports> getAllReports() {
        return reportsRepository.findAll();
    }
}
