package arom_semo.server.domain.chat.repository;

import arom_semo.server.domain.chat.model.message.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {

    public List<ChatMessage> findAllByRoomIdAndIdBeforeOrderByCreatedDateDesc(String roomId, String lastMessageId);
}