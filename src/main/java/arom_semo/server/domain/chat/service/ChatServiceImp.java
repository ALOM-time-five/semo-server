package arom_semo.server.domain.chat.service;

import arom_semo.server.domain.chat.dto.MessageDto;
import arom_semo.server.domain.chat.model.message.*;
import arom_semo.server.domain.chat.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service @Slf4j
@RequiredArgsConstructor
public class ChatServiceImp implements ChatService{
    private final ChatMessageRepository chatMessageRepository;
    @Override
    public void saveMessage(Long id, MessageDto messageDto) {

        Message message = createMessage(id, messageDto);
        if(message == null){
            throw new IllegalArgumentException("존재하지 않는 메세지 형식입니다.");
        }
        chatMessageRepository.save(message);
    }

    private Message createMessage(Long id, MessageDto messageDto) {
        MessageType type = messageDto.getType();
        if(type == MessageType.CHAT){
             return ofChatMessage(id, messageDto);
        }
        if(type == MessageType.JOIN){
            return ofJoinMessage(id, messageDto);
        }
        if(type == MessageType.LEAVE){
            return ofLeaveMessage(id, messageDto);
        }
        return null;
    }

    private static LeaveMessage ofLeaveMessage(Long id, MessageDto messageDto) {
        return LeaveMessage.builder()
                .roomId(String.valueOf(id))
                .sender(messageDto.getSender())
                .userId(messageDto.getUserId())
                .type(messageDto.getType())
                .build();
    }

    private static JoinMessage ofJoinMessage(Long id, MessageDto messageDto) {
        return JoinMessage.builder()
                .roomId(String.valueOf(id))
                .userId(messageDto.getUserId())
                .sender(messageDto.getSender())
                .type(messageDto.getType())
                .build();
    }

    private ChatMessage ofChatMessage(Long id, MessageDto messageDto) {
        return ChatMessage.builder()
                .roomId(String.valueOf(id))
                .userId(messageDto.getUserId())
                .sender(messageDto.getSender())
                .senderImageUrl(messageDto.getSenderImageUrl())
                .content(messageDto.getContent())
                .type(messageDto.getType())
                .build();
    }
}
