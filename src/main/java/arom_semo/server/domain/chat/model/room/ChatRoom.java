package arom_semo.server.domain.chat.model.room;

import arom_semo.server.global.model.BaseEntity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Document(collection = "chat_rooms")
public class ChatRoom extends BaseEntity {
    @Id
    private String id;  // MongoDB에서는 ID로 ObjectId 사용
    private String title;  // 채팅방 이름
    private List<Long> userIds = new ArrayList<>();  // 채팅방에 속한 사용자 ID 리스트

    public ChatRoom(String title, List<Long> userIds) {
        this.title = title;
        this.userIds = userIds;
    }
}
