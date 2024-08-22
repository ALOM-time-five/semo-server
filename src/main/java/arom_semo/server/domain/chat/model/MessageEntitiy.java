package arom_semo.server.domain.chat.model;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Document(collection = "chat_messages")
@NoArgsConstructor
public class MessageEntitiy {
    @Id
    private String id;
    private String sender;
    private String senderImageUrl;
    private String content;
    private String roomId;
    private LocalDateTime localDateTime;

    public MessageEntitiy(String sender, String senderImageUrl, String content, String roomId, LocalDateTime localDateTime) {
        this.sender = sender;
        this.senderImageUrl = senderImageUrl;
        this.content = content;
        this.roomId = roomId;
        this.localDateTime = localDateTime;
    }
}
