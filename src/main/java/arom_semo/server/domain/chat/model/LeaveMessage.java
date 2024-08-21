package arom_semo.server.domain.chat.model;

import org.springframework.messaging.simp.SimpMessageSendingOperations;

public class LeaveMessage implements Message{

    private String sender;
    private String roomId;

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
