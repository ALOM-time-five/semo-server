package arom_semo.server.domain.chat.dto;

import arom_semo.server.domain.chat.model.message.MessageType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MessageResponseDto {
    private String roomId;
    private String sender;
    private Long userId;
    private String senderImageUrl;
    private String content;
    private MessageType type;

    @Builder
    public MessageResponseDto(String roomId, String sender, Long userId, String senderImageUrl, String content, MessageType type) {
        this.roomId = roomId;
        this.sender = sender;
        this.userId = userId;
        this.senderImageUrl = senderImageUrl;
        this.content = content;
        this.type = type;
    }



}
