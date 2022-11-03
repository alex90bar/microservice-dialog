package ru.skillbox.diplom.group25.microservice.dialog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MessageDto
 *
 * @author Sergey Olshevskiy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Dto сообщения")
public class MessageDto {

    @Schema(description = "Id сообщения", example = "1")
    private Long id;

    @Schema(description = "Дата и время отправки", example = "1464612365")
    private Long time;

    @Schema(description = "Id автора сообщения", example = "5")
    private Long authorId;

    @Schema(description = "Id получателя сообщения", example = "8")
    private Long recipientId;

    @Schema(description = "Текст сообщения", example = "Сообщение")
    private String messageText;

    @Schema(description = "Статус прочтения", example = "SENT")
    private ReadStatusDto status;
}
