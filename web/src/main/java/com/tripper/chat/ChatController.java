package com.tripper.chat;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate template;
    private final ChatRoomRepository chatRoomRepository;

    @MessageMapping("/chat/message")
    public void message(ChatMessage message){
        if(message.getType() == ChatMessage.MessageType.ENTER) {
            message.setMessage(message.getSender() + "님이 채팅방에 입장했습니다.");
        }
        template.convertAndSend("/sub/chat/room/" + Long.toString(message.getRoomId()), message);

        ChatRoom chatRoom = chatRoomRepository.findById(message.getRoomId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채팅방입니다."));

        chatRoom.addChatMessage(message);
    }

}