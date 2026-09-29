package it.unibo.citycare.reports1.entity;

import java.util.UUID;
import java.time.LocalTime;

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
    private LocalTime timeReport;

    private String state;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalTime getTime() {
        return timeReport;
    }

    public void setTime(LocalTime timeReport) {
        this.timeReport = timeReport;
    }
}
