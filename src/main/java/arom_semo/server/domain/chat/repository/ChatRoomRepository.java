package arom_semo.server.domain.chat.repository;

import arom_semo.server.domain.chat.model.room.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {

}
