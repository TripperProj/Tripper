package com.tripper.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate template;

    @MessageMapping("/chat/message")
    public void message(ChatMessage message){
        template.convertAndSend("/sub/chat/room" + message.getRoomId(), message);
    }
}
