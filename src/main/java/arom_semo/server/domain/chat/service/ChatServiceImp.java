package arom_semo.server.domain.chat.service;

import arom_semo.server.domain.chat.dto.MessageRequestDto;
import arom_semo.server.domain.chat.dto.MessageResponseDto;
import arom_semo.server.domain.chat.model.message.*;
import arom_semo.server.domain.chat.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @Slf4j
@RequiredArgsConstructor
public class ChatServiceImp implements ChatService{
    private final ChatMessageRepository chatMessageRepository;
    @Override
    public void saveMessage(Long id, MessageRequestDto messageDto) {

        ChatMessage message = createMessage(id, messageDto);
        if(message == null){
            throw new IllegalArgumentException("메세지가 존재하지 않습니다.");
        }
        chatMessageRepository.save(message);
    }

    private ChatMessage createMessage(Long id, MessageRequestDto messageDto) {
        if(messageDto.getType() == MessageType.CHAT){
            return ChatMessage.builder()
                    .roomId(String.valueOf(id))
                    .userId(messageDto.getUserId())
                    .sender(messageDto.getSender())
                    .senderImageUrl(messageDto.getSenderImageUrl())
                    .content(messageDto.getContent())
                    .type(messageDto.getType())
                    .build();

        }else if(messageDto.getType() == MessageType.JOIN){
            return ChatMessage.builder()
                    .roomId(String.valueOf(id))
                    .sender(messageDto.getSender())
                    .content(messageDto.getSender() + "님이 입장하셨습니다.")
                    .userId(messageDto.getUserId())
                    .type(messageDto.getType())
                    .build();

        }else if(messageDto.getType() == MessageType.LEAVE){
            return ChatMessage.builder()
                    .roomId(String.valueOf(id))
                    .sender(messageDto.getSender())
                    .content(messageDto.getSender() + "님이 퇴장하셨습니다.")
                    .userId(messageDto.getUserId())
                    .type(messageDto.getType())
                    .build();
        }
        throw new IllegalArgumentException("올바르지 못한 타입이라 메세지를 생성할 수 없습니다.");

    }

    @Override
    public List<MessageResponseDto> findMessagesBy(String roomId) {
        return chatMessageRepository.findMessagesByRoomId(roomId).stream()
                .map(message -> MessageResponseDto.builder()
                        .roomId(message.getRoomId())
                        .userId(message.getUserId())
                        .sender(message.getSender())
                        .senderImageUrl(message.getSenderImageUrl())
                        .content(message.getContent())
                        .type(message.getType())
                        .build())
                .collect(Collectors.toList());

    }
}
