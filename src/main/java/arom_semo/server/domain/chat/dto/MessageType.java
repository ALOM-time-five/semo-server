package arom_semo.server.domain.chat.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MessageType {
    CHAT(new ChatMessage()),
    JOIN(new JoinMessage()),
    LEAVE(new LeaveMessage());
    //NOTIFICATION(new NotificationMessage());

    private final Message message;

}