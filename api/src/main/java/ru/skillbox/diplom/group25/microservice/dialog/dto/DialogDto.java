package ru.skillbox.diplom.group25.microservice.dialog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.skillbox.diplom.group25.microservice.account.model.AccountDto;


/**
 * DialogDto
 *
 * @author Sergey Olshevskiy
 */
@Data
@AllArgsConstructor
@Schema(description = "Dto диалога")
public class DialogDto {

    @Schema(description = "Id диалога", example = "1")
    private Long id;

    @Schema(description = "Количество непрочитанных сообщений диалога", example = "10")
    private Long unreadCount;

    @Schema(description = "Собеседник")
    private AccountDto conversationPartner;

    @Schema(description = "Последнее сообщение диалога")
    private MessageDto lastMessage;
}
