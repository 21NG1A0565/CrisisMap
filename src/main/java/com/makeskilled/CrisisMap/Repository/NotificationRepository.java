package com.makeskilled.CrisisMap.Repository;

import com.makeskilled.CrisisMap.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}