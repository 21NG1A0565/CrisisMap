package com.makeskilled.CrisisMap.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.makeskilled.CrisisMap.Entity.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
}
