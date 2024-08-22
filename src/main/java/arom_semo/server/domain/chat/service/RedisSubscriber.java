package arom_semo.server.domain.chat.service;

import arom_semo.server.domain.chat.model.MessageType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service @Slf4j
@RequiredArgsConstructor
public class RedisSubscriber implements MessageListener {
    /**
     * Redis의 메시지를 수신하여 처리하는 역할을 합니다.
     * MessageListener 인터페이스를 구현하여 Redis 메시지를 리스닝
     */

    private final ObjectMapper objectMapper;
    private final RedisTemplate redisTemplate;
    private final SimpMessageSendingOperations messagingTemplate; // WebSocket 메시지를 특정 클라이언트에게 전송하는데 사용

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            // TODO: 2024-08-21 : 메세지 객체(ChatMessageRequest) 구현,

            String publishMessage = (String) redisTemplate.getStringSerializer().deserialize(message.getBody());
            // redis 메세지 직렬화하여 문자열로 변환

            //ChatMessageRequest roomMessage = objectMapper.readValue(publishMessage, ChatMessageRequest.class);
            MessageType messageType = objectMapper.readValue(publishMessage, MessageType.class);
            arom_semo.server.domain.chat.model.Message chatMessage = messageType.getMessage();

            objectMapper.readerForUpdating(chatMessage).readValue(publishMessage); // 변환한 Message 객체를 업데이트
            chatMessage.process(messagingTemplate); // 해당 메시지를 처리

            // 문자열 -> 객체로 변환
            /*if (roomMessage.getType().equals(MessageType.TALK)) {
                GetChatMessageResponse chatMessageResponse = new GetChatMessageResponse(roomMessage);
                messagingTemplate.convertAndSend("/sub/chat/room/" + roomMessage.getRoomId(), chatMessageResponse);
                // 해당 채팅방에 메세지 전송 및 구독한 클라이언트는 메세지 수신
            }*/


        } catch (Exception e) {
            //throw new ChatMessageNotFoundException();
            throw new RuntimeException("chat 예외 발생");
        }
    }
}
