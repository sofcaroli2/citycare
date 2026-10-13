package it.unibo.citycare.solutions2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import it.unibo.citycare.solutions2.entity.Solutions;
import it.unibo.citycare.solutions2.kafka2.KafkaSolutionsDTO;
import it.unibo.citycare.solutions2.kafka2.SolutionsKafkaProducer;
import it.unibo.citycare.solutions2.repository.SolutionsRepository;

@Service
public class SolutionsService {

    @Autowired
    private SolutionsRepository solutionsRepository;

    @Autowired
    private SolutionsKafkaProducer solutionKafkaProducer;

    public List<Solutions> getAllSolutions() {
        return solutionsRepository.findAll();
    }

    public List<Solutions> findByUserIdAndState(UUID userId, Boolean state) {
        return solutionsRepository.findByUserIdAndState(userId, state);
    }

    public List<Solutions> findByState(Boolean state) {
        return solutionsRepository.findByState(state);
    }

public Solutions updateSolution(Solutions solution) {
    Solutions existing = solutionsRepository.findById(solution.getId())
            .orElseThrow(() -> new IllegalArgumentException(
                    "Soluzione non trovata: " + solution.getId()));

    existing.setDescriptionSolution(solution.getDescriptionSolution());
    existing.setTimeSolution(LocalDateTime.now());
    existing.setState(true);

    Solutions updated = solutionsRepository.save(existing);

    KafkaSolutionsDTO event = new KafkaSolutionsDTO(
            updated.getId(),
            updated.getDescriptionSolution(),
            updated.getState());

    solutionKafkaProducer.sendSolutionUpdated(event);

    return updated;
}
}
