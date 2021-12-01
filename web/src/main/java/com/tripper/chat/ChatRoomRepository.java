package com.tripper.chat;

import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;

@Repository
public class ChatRoomRepository {

    private Map<String, ChatRoom> chatRoomMap = new LinkedHashMap<>();

    public ChatRoom findRoomById (String roomId) {
        return chatRoomMap.get(roomId);
    }

    public void createChatRoom (Long boardId) {
        ChatRoom chatRoom = new ChatRoom(boardId);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
    }
}
