package ru.skillbox.diplom.group25.microservice.dialog.dialogs.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.skillbox.diplom.group25.microservice.dialog.dialogs.DeleteDialogDto;

/**
 * DeleteDialogRs
 *
 * @author Sergey Olshevskiy
 */
@Getter
@Setter
@Accessors(chain = true)
@JsonInclude(Include.NON_NULL)
@Schema(description = "Ответ на запрос удаления диалога")
public class DeleteDialogRs {

    @Schema(description = "Ошибка по запросу", example = "Неверный запрос")
    private String error;

    @Schema(description = "Описание ошибки", example = "Неверный код авторизации")
    @JsonProperty(value = "error_description")
    private String errorDescription;

    @Schema(description = "Метка времени", example = "1644234125")
    private Long timestamp;

    private DeleteDialogDto data;
}
