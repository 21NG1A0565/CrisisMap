package com.makeskilled.CrisisMap.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="messages")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sender;
    private String content;
    private long timestamp;

    public ChatMessage(String sender2, String content2, String formattedTimestamp) {
        //TODO Auto-generated constructor stub
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getFormattedTimestamp() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return formatter.format(new Date(timestamp));
    }

    public ChatMessage(Long id, String sender, String content, long timestamp) {
        this.id = id;
        this.sender = sender;
        this.content = content;
        this.timestamp = timestamp;
    }

    public ChatMessage(){}
}
