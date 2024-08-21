package arom_semo.server.domain.chat.model;

import org.springframework.messaging.simp.SimpMessageSendingOperations;

public class ChatMessage implements Message{
    private String sender;
    private String senderImageUrl;
    private String content;
    private String roomId;

    @Override
    public MessageType getType() {
        return MessageType.CHAT;
    }

    @Override
    public void process(SimpMessageSendingOperations messagingTemplate) {
        messagingTemplate.convertAndSend("/sub/chat/room/" + roomId, this);
    }
}
