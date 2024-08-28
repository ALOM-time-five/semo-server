package arom_semo.server.domain.chat.service;

import arom_semo.server.domain.chat.dto.MessageDto;

public interface ChatService {
    public void processMessage(MessageDto messageDto);
}
