package com.makeskilled.CrisisMap.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.makeskilled.CrisisMap.Entity.Emergency;

import java.util.List;

public interface EmergencyRepository extends JpaRepository<Emergency, Long> {
    List<Emergency> findBySubmittedBy(String submittedBy);
}