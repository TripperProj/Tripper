package com.tripper.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat/room")
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;

    @GetMapping("/{chatRoomId}")
    public ResponseEntity<GetChatRoomDto> getRoomInfo(@PathVariable("chatRoomId") Long id) {
        ChatRoom chatRoom = chatRoomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채팅방입니다."));

        GetChatRoomDto getChatRoomDto = new GetChatRoomDto(chatRoom.getId(), chatRoom.getMessages());

        return ResponseEntity.ok().body(getChatRoomDto);
    }

}
