package ru.skillbox.diplom.group25.microservice.dialog.dialogs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DeleteDialogDto
 *
 * @author Sergey Olshevskiy
 */
@Data
@AllArgsConstructor
@Schema(description = "Dto для id удаленного диалога")
public class DeleteDialogDto {

    @Schema(description = "Id удаленного диалога", example = "1")
    private Long id;
}
