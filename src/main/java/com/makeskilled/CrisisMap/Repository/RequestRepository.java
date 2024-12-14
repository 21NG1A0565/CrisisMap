package com.makeskilled.CrisisMap.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.makeskilled.CrisisMap.Entity.Request;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findBySubmittedBy(String submittedBy);
}
