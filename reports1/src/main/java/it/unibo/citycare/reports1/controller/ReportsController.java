package it.unibo.citycare.reports1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import it.unibo.citycare.reports1.service.ReportsService;
import it.unibo.citycare.reports1.entity.Reports;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reports")
public class ReportsController {

    @Autowired
    private ReportsService reportsService;

    @GetMapping
    public List<Reports> getAllReports() {
        return reportsService.getAllReports();
    }

    @GetMapping("/user/{userId}/state/{state}")
    public List<Reports> findByUserIdAndState(@PathVariable UUID userId, @PathVariable Boolean state) {
        return reportsService.findByUserIdAndState(userId, state);
    }

    @GetMapping("/state/{state}")
    public List<Reports> findByState(@PathVariable Boolean state) {
        return reportsService.findByState(state);
    }

    @PostMapping
    public void saveReport(@RequestBody Reports report) {
        reportsService.saveReport(report);
    }

}