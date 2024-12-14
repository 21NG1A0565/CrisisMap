package com.makeskilled.CrisisMap.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.makeskilled.CrisisMap.Entity.Report;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findBySubmittedBy(String username);
}
