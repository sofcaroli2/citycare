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

    private String title;

    @Column(name = "description_report")
    private String descriptionReport;

    private String address;

    @Column(name = "user_id")
    private UUID userId;


    private Boolean state;

    private String residence;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescriptionReport() {
        return descriptionReport;
    }

    public void setDescriptionReport(String descriptionReport) {
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

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }
}
