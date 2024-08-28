package arom_semo.server.domain.chat.dto;

import arom_semo.server.domain.chat.model.message.MessageType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MessageDto {
    private String sender;
    private Long userId;
    private String senderImageUrl;
    private String content;
    private String roomId;
    private LocalDateTime localDateTime;
    private MessageType type;
}
