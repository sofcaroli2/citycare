package it.unibo.citycare.bff.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unibo.citycare.bff.dto.SolutionsRequestDTO;
import it.unibo.citycare.bff.service.SolutionsService;

@RestController
@RequestMapping("/api/citycare/solutions")
public class SolutionsController {

    @Autowired
    private SolutionsService solutionsService;

    @GetMapping("/user/{userId}/state/{state}")
    public List<SolutionsRequestDTO> getSolutionsByUserIdAndState(@PathVariable UUID userId,
            @PathVariable Boolean state) {
        return solutionsService.getSolutionsByUserIdAndState(userId, state);
    }
}