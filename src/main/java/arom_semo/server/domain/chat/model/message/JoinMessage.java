package arom_semo.server.domain.chat.model.message;

import arom_semo.server.global.model.MongoBaseEntity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import java.time.LocalDateTime;

@Getter
@Document(collection = "chat_messages")
@NoArgsConstructor
public class JoinMessage extends MongoBaseEntity implements Message {
    @Id
    private String id;
    private String sender;
    private Long userId;
    private String roomId;
    private MessageType type;

    @Builder
    public JoinMessage(String sender, Long userId,String roomId, MessageType type) {
        this.sender = sender;
        this.roomId = roomId;
        this.userId = userId;
        this.type = type;
    }

    @Override
    public MessageType getType() {
        return MessageType.JOIN;
    }

    @Override
    public void process(SimpMessageSendingOperations messagingTemplate) {
        String joinMessageContent = sender + "님이 입장하셨습니다.";
        messagingTemplate.convertAndSend("/sub/chat/room/" + roomId, joinMessageContent);
    }
}