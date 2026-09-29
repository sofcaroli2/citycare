package it.unibo.citycare.solutions2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import it.unibo.citycare.solutions2.entity.Solutions;
import it.unibo.citycare.solutions2.repository.SolutionsRepository;

@Service
public class SolutionsService {

    @Autowired
    private SolutionsRepository solutionsRepository;

    public List<Solutions> getAllSolutions() {
        return solutionsRepository.findAll();
    }
}
