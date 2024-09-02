package arom_semo.server.domain.chat.model.message;

import arom_semo.server.domain.chat.model.room.ChatRoom;
import arom_semo.server.global.model.MongoBaseEntity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

@Getter
@Document(collection = "chat_messages")
@NoArgsConstructor
public class ChatMessage extends MongoBaseEntity{
    @Id
    private String id;
    private String sender;
    private Long userId;
    private String senderImageUrl;
    private String content;
    @DBRef
    private ChatRoom chatRoom;
    private MessageType type;

    @Builder
    public ChatMessage(String sender, Long userId, String senderImageUrl, String content, ChatRoom chatRoom, MessageType type) {
        this.sender = sender;
        this.userId = userId;
        this.senderImageUrl = senderImageUrl;
        this.content = content;
        this.chatRoom = chatRoom;
        this.type = type;
    }

    //@Override
    public void process(SimpMessageSendingOperations messagingTemplate) {

        String destination = "/sub/chat/room/" + chatRoom.getId();
        if (type == MessageType.CHAT) {
            messagingTemplate.convertAndSend(destination, this);
        } else if (type == MessageType.JOIN) {
            messagingTemplate.convertAndSend(destination, sender + "님이 입장하셨습니다.");
        } else if (type == MessageType.LEAVE) {
            messagingTemplate.convertAndSend(destination, sender + "님이 퇴장하셨습니다.");
        }
    }
}
