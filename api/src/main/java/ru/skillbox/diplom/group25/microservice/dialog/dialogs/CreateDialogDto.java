package ru.skillbox.diplom.group25.microservice.dialog.dialogs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * CreateDialogDto
 *
 * @author Sergey Olshevskiy
 */
@Data
@AllArgsConstructor
@Schema(description = "Dto для id созданного диалога")
public class CreateDialogDto {

    @Schema(description = "Id созданного диалога", example = "1")
    private Long id;
}
