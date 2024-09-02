package arom_semo.server.domain.chat.dto;

import arom_semo.server.domain.chat.model.message.MessageType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class MessageDto {
    @NotBlank(message = "회원 이름이 빈 칸일 수 없습니다.")
    private String sender;
    @NotNull(message = "사용자 고유 번호는 필수입니다.")
    private Long userId;
    private String senderImageUrl;
    @NotBlank(message = "비어 있는 내용은 전송할 수 없습니다.")
    private String content;
    @NotNull(message = "메세지 타입은 필수입니다.")
    private MessageType type;
}
