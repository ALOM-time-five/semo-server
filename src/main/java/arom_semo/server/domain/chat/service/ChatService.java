package arom_semo.server.domain.chat.service;

import arom_semo.server.domain.chat.dto.MessageRequestDto;
import arom_semo.server.domain.chat.dto.MessageResponseDto;

import java.util.List;

public interface ChatService {
    public void saveMessage(Long id, MessageRequestDto messageDto);

    public List<MessageResponseDto> findMessagesBy(String roomId);
}
