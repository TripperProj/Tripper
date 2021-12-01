package com.tripper.chat;


import lombok.Getter;

import java.util.UUID;

@Getter
public class ChatRoom {
    private String roomId;
    private Long boardId;

    public ChatRoom(Long boardId) {
        this.roomId = UUID.randomUUID().toString();
        this.boardId = boardId;
    }
}
