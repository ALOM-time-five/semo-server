package arom_semo.server.domain.chat.controller;

import arom_semo.server.domain.chat.dto.MessageDto;
import arom_semo.server.domain.chat.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController @Slf4j
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageRepository chatMessageRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(MessageDto messageDto) {
        //messageEntityRepository.save(chatMessageDto);  // MongoDB에 저장
        log.info("chatMessage = {}", messageDto);
        log.info("chatMessage.sender() = {}", messageDto.getSender());
        log.info("chatMessage.roomId() = {}", messageDto.getRoomId());
        redisTemplate.convertAndSend("chat", messageDto);  // Redis를 통해 메시지 전송
    }


    /*`@GetMapping("/api/chat/history")
    public ResponseEntity<List<ChatMessage>> getChatHistory(@RequestParam String roomId) {
        List<ChatMessage> messages = chatMessageRepository.findByRoomId(roomId);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }*/
}
