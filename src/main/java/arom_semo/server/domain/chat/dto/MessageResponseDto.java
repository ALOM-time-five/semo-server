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
    private String createdDate;

    @Builder
    public MessageResponseDto(String roomId, String sender, Long userId, String senderImageUrl, String content, MessageType type, String createdDate) {
        this.roomId = roomId;
        this.sender = sender;
        this.userId = userId;
        this.senderImageUrl = senderImageUrl;
        this.content = content;
        this.type = type;
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "MessageResponseDto{" +
                "roomId='" + roomId + '\'' +
                ", sender='" + sender + '\'' +
                ", userId=" + userId +
                ", senderImageUrl='" + senderImageUrl + '\'' +
                ", content='" + content + '\'' +
                ", type=" + type +
                ", createdDate='" + createdDate + '\'' +
                '}';
    }
}
