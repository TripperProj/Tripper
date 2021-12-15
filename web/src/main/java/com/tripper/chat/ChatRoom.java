package com.tripper.chat;


import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
public class ChatRoom {

    @Id @Column(name = "chat_room_id")
    private Long id;

    @ElementCollection
    @CollectionTable(name = "chat_message")
    private List<ChatMessage> messages = new ArrayList<>();

    protected ChatRoom() {

    }

    public ChatRoom (Long id) {
        this.id = id;
    }

    public void addChatMessage(ChatMessage message) {
        this.messages.add(message);
    }
}
