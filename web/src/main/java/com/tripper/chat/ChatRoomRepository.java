package com.tripper.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
