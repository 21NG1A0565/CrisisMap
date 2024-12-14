package com.makeskilled.CrisisMap.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.makeskilled.CrisisMap.Entity.ChatMessage;
import com.makeskilled.CrisisMap.Repository.ChatMessageRepository;


import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ChatController {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessage send(ChatMessage message) {
        message.setTimestamp(System.currentTimeMillis());
        chatMessageRepository.save(message);
        return message; // The frontend can call getFormattedTimestamp() if required
    }

    @GetMapping("/chat")
    public String chatPage() {
        return "chat";
    }
    
}
