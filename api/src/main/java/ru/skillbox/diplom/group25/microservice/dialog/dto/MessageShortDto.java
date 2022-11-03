package ru.skillbox.diplom.group25.microservice.dialog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * MessageShortDto
 *
 * @author Sergey Olshevskiy
 */
@Data
@AllArgsConstructor
@Schema(description = "Dto сообщения краткое")
public class MessageShortDto {

    @Schema(description = "Id сообщения", example = "1")
    private Long id;

    @Schema(description = "Id автора сообщения")
    private Long authorId;

    @Schema(description = "Дата и время отправки", example = "1464612365")
    private Long time;

    @Schema(description = "Текст сообщения", example = "Сообщение")
    private String messageText;
}
