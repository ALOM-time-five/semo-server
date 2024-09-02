package arom_semo.server.domain.chat.model.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MessageType {
    CHAT(new ChatMessage()),
    JOIN(new ChatMessage()),
    LEAVE(new ChatMessage());
    //NOTIFICATION(new NotificationMessage());

    private final ChatMessage message;

}