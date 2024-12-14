package com.makeskilled.CrisisMap.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.makeskilled.CrisisMap.Entity.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}