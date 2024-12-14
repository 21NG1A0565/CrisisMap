package com.makeskilled.CrisisMap.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name="emergencies")
public class Emergency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String location;
    private String description;
    private LocalDate date;

    private double latitude;
    private double longitude;

    private String status = "pending"; // Default status
    private String submittedBy;       // Track the user who submitted the emergency

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getSubmittedBy() {
        return submittedBy;
    }
    public void setSubmittedBy(String submittedBy) {
        this.submittedBy = submittedBy;
    }

    public Emergency(Long id, String title, String location, String description, LocalDate date, double latitude,
            double longitude, String status, String submittedBy) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.description = description;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
        this.submittedBy = submittedBy;
    }

    public Emergency(){}
    
}
