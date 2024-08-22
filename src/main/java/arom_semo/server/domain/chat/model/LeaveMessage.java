package arom_semo.server.domain.chat.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class LeaveMessage implements Message{

    private String sender;
    private String roomId;
    private LocalDateTime localDateTime;
    private MessageType type;


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
