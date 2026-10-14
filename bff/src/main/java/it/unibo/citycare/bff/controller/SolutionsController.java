package it.unibo.citycare.bff.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import it.unibo.citycare.bff.dto.SolutionUpdateDTO;
import it.unibo.citycare.bff.dto.SolutionsRequestDTO;
import it.unibo.citycare.bff.dto.UsersDTO;
import it.unibo.citycare.bff.service.SolutionsService;
import it.unibo.citycare.bff.service.UsersService;

@RestController
@RequestMapping("/api/citycare/solutions")
public class SolutionsController {

    @Autowired
    private SolutionsService solutionsService;

    @Autowired
    private UsersService usersService;

    @GetMapping("/user/{userId}/state/{state}")
    public List<SolutionsRequestDTO> getSolutionsByUserIdAndState(@PathVariable UUID userId,
            @PathVariable Boolean state, @AuthenticationPrincipal Jwt jwt) {
        UUID authenticatedUserId = UUID.fromString(jwt.getSubject());

        if (!authenticatedUserId.equals(userId)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Non puoi accedere alle soluzioni di un altro utente");
        }
        return solutionsService.getSolutionsByUserIdAndState(userId, state);
    }

    @GetMapping("/state/{state}")
    public List<SolutionsRequestDTO> getSolutionsByState(@PathVariable Boolean state,
            @AuthenticationPrincipal Jwt jwt) {
        UUID userId = UUID.fromString(jwt.getSubject());

        UsersDTO user = usersService.getUserById(userId);

        if (user == null || !Boolean.TRUE.equals(user.isAdmin())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Accesso consentito solo agli admin");
        }
        return solutionsService.getSolutionsByState(state);
    }

    @PutMapping
    public void updateSolution(@RequestBody SolutionUpdateDTO solution, @AuthenticationPrincipal Jwt jwt) {
        UUID userId = UUID.fromString(jwt.getSubject());
        UsersDTO user = usersService.getUserById(userId);

        if (user == null || !Boolean.TRUE.equals(user.isAdmin())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Operazione consentita solo agli admin");
        }
        solutionsService.updateSolution(solution);
    }

}