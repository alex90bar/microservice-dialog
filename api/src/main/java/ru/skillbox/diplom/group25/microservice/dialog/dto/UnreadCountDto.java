package ru.skillbox.diplom.group25.microservice.dialog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * UnreadCountDto
 *
 * @author Sergey Olshevskiy
 */
@Data
@AllArgsConstructor
@Schema(description = "Dto для количества непрочитанных сообщений диалога")
public class UnreadCountDto {

    @Schema(description = "Количество непрочитанных сообщений в диалоге", example = "10")
    private Long count;
}
