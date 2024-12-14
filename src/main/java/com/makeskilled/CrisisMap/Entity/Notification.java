package com.makeskilled.CrisisMap.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String message;
    private LocalDateTime timestamp;

    private String postedBy; // Sender of the notification
    private String postedTo; // Recipient of the notification
    private String postedType; // Public or Private

    // Getters and Setters
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getPostedTo() {
        return postedTo;
    }

    public void setPostedTo(String postedTo) {
        this.postedTo = postedTo;
    }

    public String getPostedType() {
        return postedType;
    }

    public void setPostedType(String postedType) {
        this.postedType = postedType;
    }

    public Notification(Long id, String title, String message, LocalDateTime timestamp, String postedBy,
            String postedTo, String postedType) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.timestamp = timestamp;
        this.postedBy = postedBy;
        this.postedTo = postedTo;
        this.postedType = postedType;
    }

    public Notification(){}
}