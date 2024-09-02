package arom_semo.server.domain.chat.model.room;

import arom_semo.server.global.model.MongoBaseEntity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Document(collection = "chat_rooms")
public class ChatRoom extends MongoBaseEntity {
    @Id
    private String id;  // MongoDB에서는 ID로 ObjectId 사용
    private String title;  // 채팅방 이름
    private List<Long> memberIds = new ArrayList<>();  // 채팅방에 속한 사용자 ID 리스트


    @Builder
    public ChatRoom(String title, List<Long> memberIds) {
        this.title = title;
        this.memberIds = memberIds;
    }

    public int getMemberCount(){
        return this.memberIds.size();
    }


}
