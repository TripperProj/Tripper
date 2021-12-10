package com.tripper.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ChatMessage {
    private String roomId;
    private String sender;
    private String message;
}
