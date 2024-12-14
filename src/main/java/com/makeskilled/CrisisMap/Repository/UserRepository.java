package com.makeskilled.CrisisMap.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.makeskilled.CrisisMap.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUsername(String username);
    List<User> findByRole(String role);
}
