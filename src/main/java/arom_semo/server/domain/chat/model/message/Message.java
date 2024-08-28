package arom_semo.server.domain.chat.model.message;

import org.springframework.messaging.simp.SimpMessageSendingOperations;

public interface Message {
    MessageType getType();
    void process(SimpMessageSendingOperations messagingTemplate);
}