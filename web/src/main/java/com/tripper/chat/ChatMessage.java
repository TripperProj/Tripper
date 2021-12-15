package com.tripper.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Getter @Setter
public class ChatMessage {

    public enum MessageType {
        ENTER, TALK
    }

    @Enumerated(EnumType.STRING)
    private MessageType type; // 메시지 타입
    private Long roomId;
    private String sender;
    private String message;

    protected ChatMessage() {

    }

    public ChatMessage(MessageType type, Long roomId, String sender, String message) {
        this.type = type;
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
    }
}
