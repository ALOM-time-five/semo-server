package arom_semo.server.domain.chat.controller;

import arom_semo.server.domain.chat.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController @Slf4j
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    //private final ChatMessageRepository chatMessageRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(ChatMessage chatMessage) {
        //chatMessageRepository.save(chatMessage);  // MongoDB에 저장
        log.info("chatMessage = {}", chatMessage);
        log.info("chatMessage.toString() = {}", chatMessage.getSender());
        log.info("chatMessage.toString() = {}", chatMessage.getRoomId());
        redisTemplate.convertAndSend("chat", chatMessage);  // Redis를 통해 메시지 전송
    }

    /*@GetMapping("/api/chat/history")
    public ResponseEntity<List<ChatMessage>> getChatHistory(@RequestParam String roomId) {
        List<ChatMessage> messages = chatMessageRepository.findByRoomId(roomId);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }*/
}
