package ru.skillbox.diplom.group25.microservice.dialog.dialogs;

import com.fasterxml.jackson.annotation.JsonProperty;
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
//    @JsonProperty(value = "author_id")
    private Long authorId;

    @Schema(description = "Дата и время отправки", example = "1464612365")
    private Long time;

    @Schema(description = "Текст сообщения", example = "Сообщение")
//    @JsonProperty(value = "message_text")
    private String messageText;
}
