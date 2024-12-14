package com.makeskilled.CrisisMap.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String resourceName;
    private int quantity;
    private String location;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING; // Default value

    private String submittedBy;       // Track the user who submitted the request

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getResourceName() {
        return resourceName;
    }
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public String getSubmittedBy() {
        return submittedBy;
    }
    public void setSubmittedBy(String submittedBy) {
        this.submittedBy = submittedBy;
    }
    
    public Request(Long id, String resourceName, int quantity, String location, String description, Status status,
            String submittedBy) {
        this.id = id;
        this.resourceName = resourceName;
        this.quantity = quantity;
        this.location = location;
        this.description = description;
        this.status = status;
        this.submittedBy = submittedBy;
    }

    public Request(){}
}
