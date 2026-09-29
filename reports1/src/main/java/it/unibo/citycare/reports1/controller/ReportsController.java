package it.unibo.citycare.reports1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unibo.citycare.reports1.service.ReportsService;
import it.unibo.citycare.reports1.entity.Reports;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportsController {

    @Autowired
    private ReportsService reportsService;

    @GetMapping
    public List<Reports> getAllReports() {
        return reportsService.getAllReports();
    }
}