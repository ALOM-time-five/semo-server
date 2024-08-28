package arom_semo.server.domain.chat.controller;

import arom_semo.server.domain.chat.dto.MessageDto;
import arom_semo.server.domain.chat.repository.ChatMessageRepository;
import arom_semo.server.domain.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController @Slf4j
@RequiredArgsConstructor
public class ChatController {
    //private final SimpMessagingTemplate messagingTemplate;
    private final ChatService chatService;
    private final RedisTemplate<String, Object> redisTemplate;

    @MessageMapping("/chat/room/{id}")
    public void sendMessage(@DestinationVariable("id") Long id, MessageDto messageDto) {
        chatService.saveMessage(id, messageDto);
        redisTemplate.convertAndSend("/sub/chat/room/" + id, messageDto);  // Redis를 통해 메시지 전송
        // TODO: 2024-08-28
        //  1. messageDto 대신 이를 통한 생성된 구체 객체를 넘기기( redisTemplate.convertAndSend("chat", messageDto); )
    }


    /*`@GetMapping("/api/chat/history")
    public ResponseEntity<List<ChatMessage>> getChatHistory(@RequestParam String roomId) {
        List<ChatMessage> messages = chatMessageRepository.findByRoomId(roomId);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }*/
}
