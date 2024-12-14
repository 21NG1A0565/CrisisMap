package com.makeskilled.CrisisMap.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="resources")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String resourceName;
    private int quantity;
    private String location;
    private String description;

    // Getters and setters
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

    public Resource(Long id, String resourceName, int quantity, String location, String description) {
        this.id = id;
        this.resourceName = resourceName;
        this.quantity = quantity;
        this.location = location;
        this.description = description;
    }

    public Resource() {
    }
}
