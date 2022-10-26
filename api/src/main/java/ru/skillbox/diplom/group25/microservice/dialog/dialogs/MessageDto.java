package ru.skillbox.diplom.group25.microservice.dialog.dialogs;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * MessageDto
 *
 * @author Sergey Olshevskiy
 */
@Data
@AllArgsConstructor
@Schema(description = "Dto сообщения")
public class MessageDto {

    @Schema(description = "Id сообщения", example = "1")
    private Long id;

    @Schema(description = "Дата и время отправки", example = "1464612365")
    private Long time;

    @Schema(description = "Id автора сообщения", example = "5")
//    @JsonProperty(value = "author_id")
    private Long authorId;

    @Schema(description = "Id получателя сообщения", example = "8")
//    @JsonProperty(value = "recipient_id")
    private Long recipientId;

    @Schema(description = "Текст сообщения", example = "Сообщение")
//    @JsonProperty(value = "message_text")
    private String messageText;

    @Schema(description = "Статус прочтения", example = "SENT")
    private ReadStatusDto status;
}
