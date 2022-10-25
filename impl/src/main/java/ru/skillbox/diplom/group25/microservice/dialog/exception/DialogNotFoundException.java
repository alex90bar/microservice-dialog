package ru.skillbox.diplom.group25.microservice.dialog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * DialogNotFoundException
 *
 * @author alex90bar
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = DialogNotFoundException.REASON_RU)
public class DialogNotFoundException extends RuntimeException {

    public static final String REASON_RU = "Диалог не найден в БД";
    public static final String REASON_EN = "Dialogue not found in DB";

}
