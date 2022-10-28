package ru.skillbox.diplom.group25.microservice.dialog.dialogs.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import ru.skillbox.diplom.group25.microservice.dialog.dialogs.UnreadCountDto;

/**
 * UnreadCountRs
 *
 * @author Sergey Olshevskiy
 */

@Getter
@Setter
@ToString
@Accessors(chain = true)
@JsonInclude(Include.NON_NULL)
@Schema(description = "Ответ на запрос получения количества непрочитанных сообщений диалога")
public class UnreadCountRs {

    @Schema(description = "Ошибка по запросу", example = "Неверный запрос")
    private String error;

    @Schema(description = "Описание ошибки", example = "Неверный код авторизации")
    @JsonProperty(value = "error_description")
    private String errorDescription;

    @Schema(description = "Метка времени", example = "1644234125")
    private Long timestamp;

    private UnreadCountDto data;
}
