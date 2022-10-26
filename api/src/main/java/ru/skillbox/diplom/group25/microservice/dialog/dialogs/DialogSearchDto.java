package ru.skillbox.diplom.group25.microservice.dialog.dialogs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * DialogSearchDto
 *
 * @author Sergey Olshevskiy
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dto поиска диалога")
public class DialogSearchDto {

    @Schema(description = "Id автора диалога", example = "23")
    private Long authorId;

    @Schema(description = "Id получателя диалога", example = "45")
    private Long recipientId;
}
