package it.unibo.citycare.solutions2.entity;

import java.util.UUID;
import java.time.LocalTime;

import jakarta.persistence.*;

@Entity
@Table(name = "solutions", schema = "public")
public class Solutions {
    @Id
    private UUID id;

    private String title;

    @Column(name = "description_report")
    private String descriptionReport;

    private String address;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "time_solution")
    private LocalTime timeSolution;

    @Column(name = "description_solution")
    private String descriptionSolution;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return descriptionReport;
    }

    public void setDescription(String descriptionReport) {
        this.descriptionReport = descriptionReport;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public LocalTime getTime() {
        return timeSolution;
    }

    public void setTime(LocalTime timeSolution) {
        this.timeSolution = timeSolution;
    }

    public String getDescriptionSolution() {
        return descriptionSolution;
    }

    public void setDescriptionSolution(String descriptionSolution) {
        this.descriptionSolution = descriptionSolution;
    }
}
