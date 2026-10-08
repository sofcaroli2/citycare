package it.unibo.citycare.bff.controller;

import it.unibo.citycare.bff.dto.ReportsRequestDTO;
import it.unibo.citycare.bff.service.ReportsService;
import it.unibo.citycare.bff.dto.ReportsSaveDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/citycare/reports")
public class ReportsController {

    @Autowired
    private ReportsService reportsService;

    @GetMapping("/user/{userId}/state/{state}")
    public List<ReportsRequestDTO> getReportsByUserIdAndState(@PathVariable UUID userId, @PathVariable Boolean state) {
        return reportsService.getReportsByUserIdAndState(userId, state);
    }

    @GetMapping("/state/{state}")
    public List<ReportsRequestDTO> getReportsByState(@PathVariable Boolean state) { 
        return reportsService.getReportsByState(state);
    }

    @PostMapping("/reports")
    public void saveReport(@RequestBody ReportsSaveDTO report) {
        reportsService.saveReport(report);
    }

}