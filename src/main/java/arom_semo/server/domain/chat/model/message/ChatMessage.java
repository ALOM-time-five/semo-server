package arom_semo.server.domain.chat.model.message;

import arom_semo.server.global.model.BaseEntity;
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
public class ChatMessage extends BaseEntity implements Message{
    @Id
    private String id;
    private String sender;
    private Long userId;
    private String senderImageUrl;
    private String content;
    private String roomId;
    private MessageType type;

    @Builder
    public ChatMessage(String sender, Long userId, String senderImageUrl, String content, String roomId, MessageType type) {
        this.sender = sender;
        this.userId = userId;
        this.senderImageUrl = senderImageUrl;
        this.content = content;
        this.roomId = roomId;
        this.type = type;
    }

    @Override
    public MessageType getType() {
        return MessageType.CHAT;
    }

    @Override
    public void process(SimpMessageSendingOperations messagingTemplate) {
        messagingTemplate.convertAndSend("/sub/chat/room/" + roomId, this);
    }
}
