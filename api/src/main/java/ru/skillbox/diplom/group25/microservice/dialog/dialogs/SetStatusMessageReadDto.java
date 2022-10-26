package ru.skillbox.diplom.group25.microservice.dialog.dialogs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * SetStatusMessageReadDto
 *
 * @author Sergey Olshevskiy
 */
@Data
@AllArgsConstructor
@Schema(description = "Dto ответа на запрос пометить сообщение как прочитанное")
public class SetStatusMessageReadDto {

    @Schema(description = "Сообщение о выполнении", example = "Ок")
    private String message;
}
