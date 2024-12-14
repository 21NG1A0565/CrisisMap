package com.makeskilled.CrisisMap.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.makeskilled.CrisisMap.Entity.ChatMessageDTO;
import com.makeskilled.CrisisMap.Repository.ChatMessageRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ChatHistoryController {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @GetMapping("/api/chat/history")
    public ResponseEntity<List<ChatMessageDTO>> getChatHistory() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        // Convert ChatMessage entities to DTOs
        List<ChatMessageDTO> chatHistory = chatMessageRepository.findAll().stream()
            .map(chatMessage -> new ChatMessageDTO(
                chatMessage.getSender(),
                chatMessage.getContent(),
                dateFormatter.format(new Date(chatMessage.getTimestamp())) // Format timestamp
            ))
            .collect(Collectors.toList());

        // Return the list wrapped in ResponseEntity
        return ResponseEntity.ok(chatHistory);
    }
}