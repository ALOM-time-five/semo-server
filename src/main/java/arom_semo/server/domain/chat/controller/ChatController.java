package arom_semo.server.domain.chat.controller;

import arom_semo.server.domain.chat.dto.MessageRequestDto;
import arom_semo.server.domain.chat.dto.MessageResponseDto;
import arom_semo.server.domain.chat.service.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController @Slf4j
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final RedisTemplate<String, Object> redisTemplate;

    @MessageMapping("/chat/room/{id}")
    public void sendMessage(@DestinationVariable("id") Long id, @Valid MessageRequestDto messageDto) {
        chatService.saveMessage(id, messageDto);
        redisTemplate.convertAndSend("/sub/chat/room/" + id, messageDto);  // Redis를 통해 메시지 전송
        // TODO: 2024-08-28
        //  1. messageDto 대신 이를 통한 생성된 구체 객체를 넘기기( redisTemplate.convertAndSend("chat", messageDto); )
    }


    @GetMapping("/api/chat/room/{id}")
    public ResponseEntity<List<MessageResponseDto>> getChatHistory(@PathVariable String roomId) {

        return new ResponseEntity<>(chatService.findMessagesBy(roomId), HttpStatus.OK);
    }

    // TODO: 2024-09-02 : ㅇㅇㅇ
    //  1. 이전 메세지 조회하기
    //     방 기준으로 특정 개수만큼 이전 채팅 기록 조회하기
    //  2. 채팅방 목록 조회 기능
    //      읽지 않은 채팅(오직 채팅만, 입장, 퇴장 알림은 제외) 개수 표시하기
    //      마지막 채팅 시간을 기준으로 채팅방 실시간 정리하기
}
