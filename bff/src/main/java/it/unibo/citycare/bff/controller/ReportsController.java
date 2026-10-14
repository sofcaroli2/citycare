package it.unibo.citycare.bff.controller;

import it.unibo.citycare.bff.dto.ReportsRequestDTO;
import it.unibo.citycare.bff.service.ReportsService;
import it.unibo.citycare.bff.dto.ReportsSaveDTO;
import it.unibo.citycare.bff.dto.UsersDTO;
import it.unibo.citycare.bff.service.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/citycare/reports")
public class ReportsController {

    @Autowired
    private ReportsService reportsService;

    @Autowired
    private UsersService usersService;

    @GetMapping("/user/{userId}/state/{state}")
    public List<ReportsRequestDTO> getReportsByUserIdAndState(@PathVariable UUID userId, @PathVariable Boolean state,
            @AuthenticationPrincipal Jwt jwt) {
        UUID authenticatedUserId = UUID.fromString(jwt.getSubject());

        if (!authenticatedUserId.equals(userId)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Non puoi accedere ai report di un altro utente");
        }
        return reportsService.getReportsByUserIdAndState(userId, state);
    }

    @GetMapping("/state/{state}")
    public List<ReportsRequestDTO> getReportsByState(@PathVariable Boolean state, @AuthenticationPrincipal Jwt jwt) {
        UUID userId = UUID.fromString(jwt.getSubject());

        UsersDTO user = usersService.getUserById(userId);

        if (user == null || !Boolean.TRUE.equals(user.isAdmin())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Accesso consentito solo agli admin");
        }
        return reportsService.getReportsByState(state);
    }

    @PostMapping
    public void saveReport(@RequestBody ReportsSaveDTO report, @AuthenticationPrincipal Jwt jwt) {
        UUID authenticatedUserId = UUID.fromString(jwt.getSubject());
        reportsService.saveReport(report, authenticatedUserId);
    }

}