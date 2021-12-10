package com.tripper.chat;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class ChatRoom {
    private String roomId;
    private Long boardId;
    private List<ChatMessage> messages = new ArrayList<>();

    public ChatRoom(Long boardId) {
        this.roomId = UUID.randomUUID().toString();
        this.boardId = boardId;
    }

    public void addChatMessage(ChatMessage message) {
        this.messages.add(message);
    }
}
