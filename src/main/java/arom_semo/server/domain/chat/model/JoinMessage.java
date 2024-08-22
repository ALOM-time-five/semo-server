package arom_semo.server.domain.chat.model;

import org.springframework.messaging.simp.SimpMessageSendingOperations;

public class JoinMessage implements Message {
    private String sender;
    private String roomId;
    private MessageType type;

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