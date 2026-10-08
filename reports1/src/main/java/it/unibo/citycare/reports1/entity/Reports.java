package it.unibo.citycare.reports1.entity;

import java.util.UUID;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "reports", schema = "public")
public class Reports {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    @Column(name = "description_report")
    private String descriptionReport;

    private String address;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "time_report")
    private LocalDateTime timeReport;

    private Boolean state;

    private String residence;

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

    public LocalDateTime getTimeReport() {
        return timeReport;
    }

    public void setTimeReport(LocalDateTime timeReport) {
        this.timeReport = timeReport;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }
}
