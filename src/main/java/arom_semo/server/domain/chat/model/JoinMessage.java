package arom_semo.server.domain.chat.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class JoinMessage implements Message {
    private String sender;
    private String roomId;
    private LocalDateTime localDateTime;
    private MessageType type;


    public JoinMessage(String sender, String roomId, MessageType type) {
        this.sender = sender;
        this.roomId = roomId;
        this.localDateTime = LocalDateTime.now();
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