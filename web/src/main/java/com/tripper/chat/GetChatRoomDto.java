package com.tripper.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class GetChatRoomDto {
    private String roomId;
    private Long boardId;
    private List<ChatMessage> messages = new ArrayList<>();
}
