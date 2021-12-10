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

    @MessageMapping("/chat/enter")
    public ResponseEntity enter(ChatMessage message){
        message.setMessage(message.getSender() + "님이 채팅방에 입장했습니다.");
        template.convertAndSend("/sub/chat/room" + message.getRoomId(), message);

        ChatRoom chatRoom = chatRoomRepository.findRoomById(message.getRoomId());
        chatRoom.addChatMessage(message);
        List<ChatMessage> messages = new ArrayList<>(chatRoom.getMessages());
        GetChatRoomDto getChatRoomDto = new GetChatRoomDto(chatRoom.getRoomId(), chatRoom.getBoardId(), messages);

        return ResponseEntity.ok().body(getChatRoomDto);
    }

    @MessageMapping("/chat/message")
    public void message(ChatMessage message){
        template.convertAndSend("/sub/chat/room" + message.getRoomId(), message);
        ChatRoom chatRoom = chatRoomRepository.findRoomById(message.getRoomId());
        chatRoom.addChatMessage(message);
    }

    @ApiOperation(
            value = "채팅방 생성"
            , notes = "친구찾기 게시판 아이디를 이용해 채팅방을 생성한다.")
    @PostMapping("/chat/{boardId}")
    public ResponseEntity createChatRoom(@PathVariable("boardId") Long boardId) {
        ChatRoom chatRoom = new ChatRoom(boardId);
        chatRoomRepository.save(chatRoom);
        return ResponseEntity.ok(chatRoom.getRoomId());
    }
}