package arom_semo.server.domain.chat.model.message;

import arom_semo.server.global.model.MongoBaseEntity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.messaging.simp.SimpMessageSendingOperations;


@Getter
@Document(collection = "chat_messages")
@NoArgsConstructor
public class LeaveMessage extends MongoBaseEntity implements Message {
    @Id
    private String id;
    private String sender;
    private String roomId;
    private Long userId;
    private MessageType type;

    @Builder
    public LeaveMessage(String sender, String roomId, Long userId, MessageType type) {
        this.sender = sender;
        this.roomId = roomId;
        this.userId = userId;
        this.type = type;
    }

    @Override
    public MessageType getType() {
        return MessageType.LEAVE;
    }

    @Override
    public void process(SimpMessageSendingOperations messagingTemplate) {
        String leaveMessageContent = sender + "님이 퇴장하셨습니다.";
        messagingTemplate.convertAndSend("/sub/chat/room/" + roomId, leaveMessageContent);
    }
}
