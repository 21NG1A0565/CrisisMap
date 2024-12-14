package com.makeskilled.CrisisMap.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.makeskilled.CrisisMap.Entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAssignedNgo_Username(String username);
}
