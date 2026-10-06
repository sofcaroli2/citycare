package it.unibo.citycare.solutions2.entity;

import java.util.UUID;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "solutions", schema = "public")
public class Solutions {
    @Id
    private UUID id;

    @Column(name = "time_solution")
    private LocalDateTime timeSolution;

    @Column(name = "description_solution")
    private String descriptionSolution;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getTimeSolution() {
        return timeSolution;
    }

    public void setTimeSolution(LocalDateTime timeSolution) {
        this.timeSolution = timeSolution;
    }

    public String getDescriptionSolution() {
        return descriptionSolution;
    }

    public void setDescriptionSolution(String descriptionSolution) {
        this.descriptionSolution = descriptionSolution;
    }
}
