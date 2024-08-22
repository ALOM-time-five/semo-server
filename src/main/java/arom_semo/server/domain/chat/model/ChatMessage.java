package arom_semo.server.domain.chat.model;

import org.springframework.messaging.simp.SimpMessageSendingOperations;

import java.time.LocalDateTime;

public class ChatMessage implements Message{
    private String sender;
    private String senderImageUrl;
    private String content;
    private String roomId;
    private LocalDateTime localDateTime;

    public ChatMessage() {
        this.localDateTime = LocalDateTime.now();
    }

    public ChatMessage(String sender, String senderImageUrl, String content, String roomId) {
        this.sender = sender;
        this.senderImageUrl = senderImageUrl;
        this.content = content;
        this.roomId = roomId;
        this.localDateTime = LocalDateTime.now();
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
