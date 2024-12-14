package com.makeskilled.CrisisMap.Entity;

public class ChatMessageDTO {
    private String sender;
    private String content;
    private String timestamp;

    public ChatMessageDTO(String sender, String content, String timestamp) {
        this.sender = sender;
        this.content = content;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
