package arom_semo.server.domain.chat.model;

import lombok.Getter;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import java.time.LocalDateTime;

@Getter
public class ChatMessage implements Message{
    private String sender;
    private String senderImageUrl;
    private String content;
    private String roomId;
    private LocalDateTime localDateTime;
    private MessageType type;

    public ChatMessage() {
        this.localDateTime = LocalDateTime.now();
    }

    public ChatMessage(String sender, String senderImageUrl, String content, String roomId, MessageType type) {
        this.sender = sender;
        this.senderImageUrl = senderImageUrl;
        this.content = content;
        this.roomId = roomId;
        this.localDateTime = LocalDateTime.now();
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
