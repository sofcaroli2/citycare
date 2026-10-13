package it.unibo.citycare.solutions2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unibo.citycare.solutions2.service.SolutionsService;
import it.unibo.citycare.solutions2.entity.Solutions;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/solutions")
public class SolutionsController {

    @Autowired
    private SolutionsService solutionsService;
  
    @GetMapping
    public List<Solutions> getAllSolutions() {
        return solutionsService.getAllSolutions();
    }

    @GetMapping("/user/{userId}/state/{state}")
    public List<Solutions> findByUserIdAndState(@PathVariable UUID userId, @PathVariable Boolean state) {
        return solutionsService.findByUserIdAndState(userId, state);
    }

    @GetMapping("/state/{state}")
    public List<Solutions> findByState(@PathVariable Boolean state) {
        return solutionsService.findByState(state);
    }

    // @PostMapping
    // public void saveReport(@RequestBody Solutions solutions) {
    //     solutionsService.saveSolutions(solutions);
    // }
}