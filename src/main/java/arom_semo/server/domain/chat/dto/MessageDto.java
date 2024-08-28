package arom_semo.server.domain.chat.dto;

import arom_semo.server.domain.chat.model.message.MessageType;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class MessageDto {
    private String sender;
    private Long userId;
    private String senderImageUrl;
    private String content;
    private MessageType type;
}
