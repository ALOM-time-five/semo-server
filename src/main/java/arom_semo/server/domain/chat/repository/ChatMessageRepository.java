package arom_semo.server.domain.chat.repository;

import arom_semo.server.domain.chat.model.message.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepository extends MongoRepository<Message, String> {
}