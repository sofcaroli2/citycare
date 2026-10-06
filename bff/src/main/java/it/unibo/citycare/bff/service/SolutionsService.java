package it.unibo.citycare.bff.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import it.unibo.citycare.bff.client.SolutionsClient;
import it.unibo.citycare.bff.dto.SolutionsDTO;
import it.unibo.citycare.bff.dto.SolutionsRequestDTO;

@Service
public class SolutionsService {

    private final SolutionsClient solutionsClient;

    public SolutionsService(SolutionsClient solutionsClient) {
        this.solutionsClient = solutionsClient;
    }

    public List<SolutionsRequestDTO> getSolutionsByUserIdAndState(UUID userId, Boolean state) {
        SolutionsDTO[] solutions = solutionsClient.getSolutionsByUserIdAndState(userId, state);
        return Arrays.stream(solutions)
                .map(solution -> new SolutionsRequestDTO(
                        solution.id(),
                        solution.title(),
                        solution.descriptionSolution(),
                        solution.address(),
                        solution.timeSolution()
                ))
                .collect(Collectors.toList());
    }
}