package ru.skillbox.diplom.group25.microservice.dialog.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.skillbox.diplom.group25.microservice.dialog.dto.DialogDto;

/**
 * GetDialogsRs
 *
 * @author Sergey Olshevskiy
 */
@Getter
@Setter
@Accessors(chain = true)
@JsonInclude(Include.NON_NULL)
@Schema(description = "Ответ на запрос получения списка диалогов пользователя")
public class GetDialogsRs {

    @Schema(description = "Ошибка по запросу", example = "Неверный запрос")
    private String error;

    @Schema(description = "Описание ошибки", example = "Неверный код авторизации")
//    @JsonProperty(value = "error_description")
    private String errorDescription;

    @Schema(description = "Метка времени", example = "1644234125")
    private Long timestamp;

    @Schema(description = "Количество диалогов пользователя в списке", example = "10")
    private Integer total;

    @Schema(description = "Отступ от начала списка", example = "0")
    private Integer offset;

    @Schema(description = "Количество диалогов пользователя на страницу", example = "20")
    private Integer perPage;

    @Schema(description = "Id текущего пользователя", example = "55")
    private Long currentUserId;

    @Schema(description = "Список диалогов пользователя")
    private List<DialogDto> data;
}
