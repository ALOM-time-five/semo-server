package arom_semo.server.domain.chat.service;

import arom_semo.server.domain.chat.dto.MessageRequestDto;
import arom_semo.server.domain.chat.dto.MessageResponseDto;
import arom_semo.server.domain.chat.model.message.*;
import arom_semo.server.domain.chat.model.room.ChatRoom;
import arom_semo.server.domain.chat.repository.ChatMessageRepository;
import arom_semo.server.domain.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service @Slf4j
@RequiredArgsConstructor
public class ChatServiceImp implements ChatService{
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    @Override
    public void saveMessage(String roomId, MessageRequestDto messageDto) {
        ChatRoom chatRoom = chatRoomRepository.findById(roomId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채팅방 입니다."));

        ChatMessage message = createMessage(chatRoom, messageDto);
        if(message == null){
            throw new IllegalArgumentException("메세지가 존재하지 않습니다.");
        }
        chatMessageRepository.save(message);
    }

    private ChatMessage createMessage(ChatRoom chatRoom, MessageRequestDto messageDto) {
        if(messageDto.getType() == MessageType.CHAT){
            return ChatMessage.builder()
                    .roomId(chatRoom.getId())
                    .userId(messageDto.getUserId())
                    .sender(messageDto.getSender())
                    .senderImageUrl(messageDto.getSenderImageUrl())
                    .content(messageDto.getContent())
                    .type(messageDto.getType())
                    .build();

        }else if(messageDto.getType() == MessageType.JOIN){
            return ChatMessage.builder()
                    .roomId(chatRoom.getId())
                    .sender(messageDto.getSender())
                    .content(messageDto.getSender() + "님이 입장하셨습니다.")
                    .userId(messageDto.getUserId())
                    .type(messageDto.getType())
                    .build();

        }else if(messageDto.getType() == MessageType.LEAVE){
            return ChatMessage.builder()
                    .roomId(chatRoom.getId())
                    .sender(messageDto.getSender())
                    .content(messageDto.getSender() + "님이 퇴장하셨습니다.")
                    .userId(messageDto.getUserId())
                    .type(messageDto.getType())
                    .build();
        }
        throw new IllegalArgumentException("올바르지 못한 타입이라 메세지를 생성할 수 없습니다.");

    }

    @Override
    public List<MessageResponseDto> findMessagesBy(String roomId, String lastId) {
        //ChatRoom chatRoom = chatRoomRepository.findById(roomId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채팅방 입니다."));
        LocalDateTime
        List<MessageResponseDto> result = chatMessageRepository.findAllByRoomIdAndIdBeforeOrderByCreatedDateDesc(roomId/*chatRoom.getId()*/, lastId).stream()
                .limit(30)
                .map(message -> MessageResponseDto.builder()
                        .roomId(message.getRoomId())
                        .userId(message.getUserId())
                        .sender(message.getSender())
                        .senderImageUrl(message.getSenderImageUrl())
                        .content(message.getContent())
                        .type(message.getType())
                        .createdDate(message.getCreatedDate().format(DateTimeFormatter.ISO_DATE_TIME))
                        .build())
                .collect(Collectors.toList());
        for(MessageResponseDto dto : result){
            log.info("dto = {}", dto.toString());
        }

        return result;

    }
}
