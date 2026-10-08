package it.unibo.citycare.bff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unibo.citycare.bff.client.ReportsClient;
import it.unibo.citycare.bff.dto.ReportsRequestDTO;
import it.unibo.citycare.bff.dto.ReportsDTO;
import it.unibo.citycare.bff.dto.ReportsSaveDTO;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReportsService {

    public ReportsService(ReportsClient reportsClient) {
        this.reportsClient = reportsClient;
    }

    @Autowired
    private final ReportsClient reportsClient;

    // trasforma un array di ReportsDTO in una lista di ReportsRequestDTO
    public List<ReportsRequestDTO> getReportsByUserIdAndState(UUID userId, Boolean state) {
        ReportsDTO[] reportsDTO = reportsClient.getReportsByUserIdAndState(userId, state);
        return Arrays.stream(reportsDTO)
                .map(reportDTO -> new ReportsRequestDTO(
                        reportDTO.title(),
                        reportDTO.descriptionReport(),
                        reportDTO.address(),
                        reportDTO.timeReport(),
                        reportDTO.residence()
                    ))
                .collect(Collectors.toList());
    }

    public List<ReportsRequestDTO> getReportsByState(Boolean state) {
        ReportsDTO[] reportsDTO = reportsClient.getReportsByState(state);
        return Arrays.stream(reportsDTO)
                .map(reportDTO -> new ReportsRequestDTO(
                        reportDTO.title(),
                        reportDTO.descriptionReport(),
                        reportDTO.address(),
                        reportDTO.timeReport(),
                        reportDTO.residence()
                    ))
                .collect(Collectors.toList());
    }

    public void saveReport(ReportsSaveDTO report) {
        reportsClient.saveReport(report);
    }

}
