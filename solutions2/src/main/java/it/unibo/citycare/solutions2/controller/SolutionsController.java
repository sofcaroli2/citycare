package it.unibo.citycare.solutions2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unibo.citycare.solutions2.service.SolutionsService;
import it.unibo.citycare.solutions2.entity.Solutions;

import java.util.List;

@RestController
@RequestMapping("/solutions")
public class SolutionsController {

    @Autowired
    private SolutionsService solutionsService;

    @GetMapping
    public List<Solutions> getAllSolutions() {
        return solutionsService.getAllSolutions();
    }
}