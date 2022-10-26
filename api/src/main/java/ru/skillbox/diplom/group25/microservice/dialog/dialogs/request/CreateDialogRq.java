package ru.skillbox.diplom.group25.microservice.dialog.dialogs.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * CreateDialogRq
 *
 * @author Sergey Olshevskiy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Запрос на создание диалога")
public class CreateDialogRq {

    @Schema(description = "Id пользователей, c которыми создается диалог")
    @JsonProperty(value = "user_ids")
    private List<Long> userIds;
}
