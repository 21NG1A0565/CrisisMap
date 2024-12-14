package com.makeskilled.CrisisMap.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String taskName;
    private String description;

    @ManyToOne
    private User assignedNgo; // Reference the User entity with NGO role

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING; // Default value

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAssignedNgo() {
        return assignedNgo;
    }

    public void setAssignedNgo(User assignedNgo) {
        this.assignedNgo = assignedNgo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Task(Long id, String taskName, String description, User assignedNgo,Status status) {
        this.id = id;
        this.taskName = taskName;
        this.description = description;
        this.assignedNgo = assignedNgo;
        this.status=status;
    }

    public Task(){}
}